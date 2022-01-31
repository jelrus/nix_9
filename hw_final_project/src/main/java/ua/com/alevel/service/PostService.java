package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;

import java.util.List;

public interface PostService extends BaseService<Post>{

    List<Post> findPostsFiltered();

    List<Post> findPostsByUserId(Long userId);

    User findUserByPostId(Long postId);
}
