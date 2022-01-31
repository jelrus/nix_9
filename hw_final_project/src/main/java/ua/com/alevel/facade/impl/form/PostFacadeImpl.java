package ua.com.alevel.facade.impl.form;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PostFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.service.PostService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostFacadeImpl implements PostFacade {

    private final PostService postService;

    public PostFacadeImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void create(PostDtoRequest postDtoRequest) {
        Post post = new Post();
        post.setHeaderPic(postDtoRequest.getHeaderPic());
        post.setTitle(postDtoRequest.getTitle());
        post.setBody(postDtoRequest.getBody());
        post.setComments(postDtoRequest.getComments());
        postService.create(post);
    }

    @Override
    public void update(PostDtoRequest postDtoRequest, Long id) {
        Post post = postService.findById(id).get();
        post.setHeaderPic(postDtoRequest.getHeaderPic());
        post.setTitle(postDtoRequest.getTitle());
        post.setBody(postDtoRequest.getBody());
        post.setComments(postDtoRequest.getComments());
        postService.update(post);
    }

    @Override
    public void delete(Long id) {
        postService.delete(id);
    }

    @Override
    public PostDtoResponse findById(Long id) {
        return new PostDtoResponse(postService.findById(id).get());
    }

    @Override
    public PageData<PostDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Post> posts = postService.findAll(dataTableRequest);
        List<PostDtoResponse> responseList = toDtoList(posts);
        PageData<PostDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(posts.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<PostDtoResponse> findAllPostsFiltered() {
        List<PostDtoResponse> dtoResponses = new ArrayList<>();
        List<Post> posts = postService.findPostsFiltered();
        for (Post post: posts) {
            PostDtoResponse dtoResponse = new PostDtoResponse(post);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    @Override
    public List<PostDtoResponse> findAllPostsByUserId(Long userId) {
        List<Post> allUserPosts = postService.findPostsByUserId(userId);
        List<PostDtoResponse> dtoResponses = new ArrayList<>();
        for (Post post: allUserPosts) {
            PostDtoResponse dtoResponse = new PostDtoResponse(post);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    @Override
    public UserDtoResponse findUserByPostId(Long postId) {
        return new UserDtoResponse(postService.findUserByPostId(postId));
    }

    private List<PostDtoResponse> toDtoList(DataTableResponse<Post> posts) {
        return posts.getItems()
                    .stream()
                    .map(PostDtoResponse::new)
                    .collect(Collectors.toList());
    }
}
