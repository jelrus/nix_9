package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;

import java.util.List;

public interface CommentService extends BaseService<Comment>{

    List<Comment> findCommentsFiltered();

    List<Comment> findCommentsByUserId(Long userId);

    List<Comment> findCommentsByPostId(Long postId);

    User findUserByCommentId(Long commentId);

    Post findPostByCommentId(Long commentId);
}
