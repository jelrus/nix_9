package ua.com.alevel.persistence.repository.form;

import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface PostRepository extends BaseRepository<Post> {

    List<Post> findByVisibleTrueOrderByCreatedDesc();

    List<Post> findByVisibleTrueAndUserIdOrderByCreatedDesc(Long userId);

}
