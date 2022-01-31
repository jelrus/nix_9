package ua.com.alevel.facade.impl.form;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CommentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.service.CommentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.facades.Converter;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.form.CommentDtoResponse;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentFacadeImpl implements CommentFacade {

    private final CommentService commentService;

    public CommentFacadeImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void create(CommentDtoRequest commentDtoRequest) {
        Comment comment = new Comment();
        comment.setBody(commentDtoRequest.getBody());
        commentService.create(comment);
    }

    @Override
    public void update(CommentDtoRequest commentDtoRequest, Long id) {
        Comment comment = commentService.findById(id).get();
        comment.setBody(commentDtoRequest.getBody());
        commentService.update(comment);
    }

    @Override
    public void delete(Long id) {
        commentService.delete(id);
    }

    @Override
    public CommentDtoResponse findById(Long id) {
        return new CommentDtoResponse(commentService.findById(id).get());
    }

    @Override
    public PageData<CommentDtoResponse> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = Converter.pageAndSortDataToDtoReq(pageAndSizeData, sortData);
        DataTableResponse<Comment> comments = commentService.findAll(dataTableRequest);
        List<CommentDtoResponse> responseList = toDtoList(comments);
        PageData<CommentDtoResponse> pageData = Converter.dtoRespToPageAndSortData(responseList, pageAndSizeData, sortData);
        pageData.setItemsSize(comments.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<CommentDtoResponse> findAllCommentsFiltered() {
        List<CommentDtoResponse> dtoResponses = new ArrayList<>();
        List<Comment> comments = commentService.findCommentsFiltered();
        for (Comment comment: comments) {
            CommentDtoResponse dtoResponse = new CommentDtoResponse(comment);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    @Override
    public List<CommentDtoResponse> findAllCommentsByPostId(Long postId) {
        List<Comment> allUserComments = commentService.findCommentsByPostId(postId);
        List<CommentDtoResponse> dtoResponses = new ArrayList<>();
        for (Comment comment: allUserComments) {
            CommentDtoResponse dtoResponse = new CommentDtoResponse(comment);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    @Override
    public List<CommentDtoResponse> findAllCommentsByUserId(Long userId) {
        List<Comment> allUserComments = commentService.findCommentsByUserId(userId);
        List<CommentDtoResponse> dtoResponses = new ArrayList<>();
        for (Comment comment: allUserComments) {
            CommentDtoResponse dtoResponse = new CommentDtoResponse(comment);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    @Override
    public UserDtoResponse findUserByCommentId(Long commentId) {
        return new UserDtoResponse(commentService.findUserByCommentId(commentId));
    }

    @Override
    public PostDtoResponse findPostByCommentId(Long commentId) {
        return new PostDtoResponse(commentService.findPostByCommentId(commentId));
    }

    private List<CommentDtoResponse> toDtoList(DataTableResponse<Comment> comments) {
        return comments.getItems()
                .stream()
                .map(CommentDtoResponse::new)
                .collect(Collectors.toList());
    }
}
