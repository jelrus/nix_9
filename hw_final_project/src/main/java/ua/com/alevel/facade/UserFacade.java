package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.form.CommentDtoRequest;
import ua.com.alevel.view.dto.request.form.PostDtoRequest;
import ua.com.alevel.view.dto.request.user.UserDtoRequest;
import ua.com.alevel.view.dto.response.user.UserDtoResponse;

public interface UserFacade extends BaseFacade<UserDtoRequest, UserDtoResponse>{

    Long findIdByUsername(String username);

    UserDtoResponse findByUsername(String username);

    String findUsernameById(Long id);

    void createPost(String username, PostDtoRequest postDtoReq);

    void deletePost(String username, Long postId);

    void createComment(String username, Long postId, CommentDtoRequest commentDtoReq);

    void deleteComment(String username, Long postId, Long commentId);
}
