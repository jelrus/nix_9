package ua.com.alevel.persistence.repository.form;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.persistence.entity.form.Opinion;

import java.util.LinkedList;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    LinkedList<Opinion> findOpinionsByPostId(Long postId);
}
