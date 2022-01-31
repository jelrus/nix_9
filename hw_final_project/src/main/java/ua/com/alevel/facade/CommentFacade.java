package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.form.CommentDtoResponse;
import ua.com.alevel.view.dto.response.form.PostDtoResponse;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

import java.util.List;

public interface CommentFacade extends BaseFacade<CommentDtoRequest, CommentDtoResponse> {

    List<CommentDtoResponse> findAllCommentsFiltered();

    List<CommentDtoResponse> findAllCommentsByPostId(Long postId);

    List<CommentDtoResponse> findAllCommentsByUserId(Long userId);

    UserDtoResponse findUserByCommentId(Long commentId);

    PostDtoResponse findPostByCommentId(Long commentId);
}
