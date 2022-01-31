package ua.com.alevel.facade.impl.user;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.StandardUserFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.StandardUserService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.request.user.UserDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardUserFacadeImpl implements StandardUserFacade {

    private final StandardUserService standardUserService;

    public StandardUserFacadeImpl(StandardUserService standardUserService) {
        this.standardUserService = standardUserService;
    }

    @Override
    public void create(UserDtoRequest standardUserDtoRequest) {
        User standardUser = new User();
        standardUser.setUsername(standardUser.getUsername());
        standardUser.setProfilePic(standardUserDtoRequest.getProfilePic());
        standardUser.setDescription(standardUserDtoRequest.getDescription());
        standardUser.setFirstName(standardUserDtoRequest.getFirstName());
        standardUser.setLastName(standardUserDtoRequest.getLastName());
        standardUserService.create(standardUser);
    }

    @Override
    public void update(UserDtoRequest standardUserDtoRequest, Long id) {
        User standardUser = standardUserService.findById(id).get();
        standardUser.setUsername(standardUser.getUsername());
        standardUser.setProfilePic(standardUserDtoRequest.getProfilePic());
        standardUser.setDescription(standardUserDtoRequest.getDescription());
        standardUser.setFirstName(standardUserDtoRequest.getFirstName());
        standardUser.setLastName(standardUserDtoRequest.getLastName());
        standardUserService.update(standardUser);
    }

    @Override
    public void delete(Long id) {
        standardUserService.delete(id);
    }

    @Override
    public UserDtoResponse findById(Long id) {
        return new UserDtoResponse(standardUserService.findById(id).get());
    }

    @Override
    public PageData<UserDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<User> users = standardUserService.findAll(dataTableRequest);
        List<UserDtoResponse> responseList = toDtoList(users);
        PageData<UserDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(users.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Long findIdByUsername(String username) {
        return standardUserService.getIdByUsername(username);
    }

    @Override
    public UserDtoResponse findByUsername(String username) {
        return new UserDtoResponse(standardUserService.getUserByUsername(username));
    }

    @Override
    public String findUsernameById(Long id) {
        return standardUserService.findById(id).get().getUsername();
    }

    @Override
    public void createPost(String username, PostDtoRequest postDtoRequest) {
        Post post = new Post();
        post.setHeaderPic(postDtoRequest.getHeaderPic());
        post.setTitle(postDtoRequest.getTitle());
        post.setBody(postDtoRequest.getBody());
        post.setLikes(0L);
        post.setDislikes(0L);
        post.setRating(0L);
        post.setComments(postDtoRequest.getComments());
        standardUserService.createPost(username, post);
    }

    @Override
    public void deletePost(String username, Long postId) {
        standardUserService.deletePost(username, postId);
    }

    @Override
    public void createComment(String username, Long postId, CommentDtoRequest commentDtoRequest) {
        Comment comment = new Comment();
        comment.setBody(commentDtoRequest.getBody());
        standardUserService.createComment(username, postId, comment);
    }

    @Override
    public void deleteComment(String username, Long postId, Long commentId) {
        standardUserService.deleteComment(username, postId, commentId);
    }

    private List<UserDtoResponse> toDtoList(DataTableResponse<User> users) {
        return users.getItems().
                stream().
                map(UserDtoResponse::new).
                collect(Collectors.toList());
    }
}
