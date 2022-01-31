package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;

public interface UserService extends BaseService<User>{

    Long getIdByUsername(String username);

    User getUserByUsername(String username);

    void createPost(String username, Post post);

    void deletePost(String username, Long postId);

    void createComment(String username, Long postId, Comment comment);

    void deleteComment(String username, Long postId, Long commentId);
}
