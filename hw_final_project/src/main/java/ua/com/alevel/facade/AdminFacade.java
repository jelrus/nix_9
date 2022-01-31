package ua.com.alevel.facade;

public interface AdminFacade extends UserFacade {

    void disablePost(Long postId);

    void disableComment(Long commentId);

    void disableUser(Long userId);

    Integer getGlobalPosts();

    Integer getGlobalComments();

    Integer getGlobalLikes();

    Integer getGlobalDislikes();
}
