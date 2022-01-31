package ua.com.alevel.persistence.repository.form;

import org.springframework.data.domain.Pageable;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {

    List<Comment> findByVisibleTrueOrderByCreatedDesc();

    List<Comment> findByVisibleTrueAndUserIdOrderByCreatedDesc(Long userId);

    List<Comment> findByVisibleTrueAndPostIdOrderByCreatedDesc(Long postId);

}
