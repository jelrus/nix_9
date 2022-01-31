package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.form.Opinion;

import java.util.List;

public interface OpinionService {

    Long getNegativeOpinionByPost(Long postId);

    Long getPositiveOpinionByPost(Long postId);

    void getSummaryOpinionByPost(Long postId);

    void makeNegativeOpinion(Long userId, Long postId);

    void makePositiveOpinion(Long userId, Long postId);

    List<Opinion> findOpinionsByPostId(Long postId);

    Integer opinionStatus(Long postId);

    List<Opinion> findAll();

    Long getTotalLikes(Long userId);

    Long getTotalDislikes(Long userId);

    Long getTotalRating(Long userId);
}
