package ua.com.alevel.service;

public interface AdminService extends UserService {

    void disablePost(Long postId);

    void disableComment(Long commentId);

    void disableUser(Long userId);

    Integer getGlobalPosts();

    Integer getGlobalComments();

    Integer getGlobalLikes();

    Integer getGlobalDislikes();
}
