package ua.com.alevel.service.form;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.form.Opinion;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.form.type.OpinionType;
import ua.com.alevel.persistence.repository.form.OpinionRepository;
import ua.com.alevel.service.OpinionService;
import ua.com.alevel.service.PostService;

import java.util.*;

@Service
public class OpinionServiceImpl implements OpinionService {

    private final PostService postService;
    private final OpinionRepository opinionRepository;

    public OpinionServiceImpl(PostService postService, OpinionRepository opinionRepository) {
        this.postService = postService;
        this.opinionRepository = opinionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getNegativeOpinionByPost(Long postId) {
        List<Opinion> allOpinions = findOpinionsByPostId(postId);
        long count = 0;
        for(Opinion op: allOpinions){
            if (op.getOpinionType().getValue().equals(-1)){
                count--;
            }
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPositiveOpinionByPost(Long postId) {
        List<Opinion> allOpinions = findOpinionsByPostId(postId);
        long count = 0;
        for(Opinion op: allOpinions){
            if (op.getOpinionType().getValue().equals(1)){
                count++;
            }
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public void getSummaryOpinionByPost(Long postId) {
        Post post = postService.findById(postId).get();
        post.setRating(getNegativeOpinionByPost(postId) + getPositiveOpinionByPost(postId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void makeNegativeOpinion(Long userId, Long postId) {
       Post post = postService.findById(postId).get();
       long dislikes = post.getDislikes() - 1;
       post.setDislikes(dislikes);
        post.setRating(post.getLikes()+post.getDislikes());
       opinionRepository.save(createNegativeOpinion(userId, postId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void makePositiveOpinion(Long userId, Long postId) {
        Post post = postService.findById(postId).get();
        long likes = post.getLikes() + 1;
        post.setLikes(likes);
        post.setRating(post.getLikes()+post.getDislikes());
        opinionRepository.save(createPositiveOpinion(userId, postId));
    }

    private Opinion createNegativeOpinion(Long userId, Long postId){
        Opinion opinion = new Opinion();
        opinion.setOpinionType(OpinionType.NEGATIVE);
        opinion.setPostId(postId);
        opinion.setUserId(userId);
        return opinion;
    }

    private Opinion createPositiveOpinion(Long userId, Long postId){
        Opinion opinion = new Opinion();
        opinion.setOpinionType(OpinionType.POSITIVE);
        opinion.setPostId(postId);
        opinion.setUserId(userId);
        return opinion;
    }

    @Override
    @Transactional
    public LinkedList<Opinion> findOpinionsByPostId(Long postId) {
        LinkedList<Opinion> opinions = new LinkedList<>();
        for(Opinion opinion: opinionRepository.findAll()){
            if (opinion.getPostId().equals(postId)){
                opinions.add(opinion);
            }
        }
        if (opinions.isEmpty()) {
            Opinion opinion = new Opinion();
            opinion.setPostId(postId);
            opinion.setOpinionType(OpinionType.NEUTRAL);
            opinions.add(opinion);
        }
        return opinions;
    }

    @Override
    @Transactional
    public Integer opinionStatus(Long postId) {
        return findOpinionsByPostId(postId).get(findOpinionsByPostId(postId).size()-1).getOpinionType().getValue();
    }

    @Override
    @Transactional
    public List<Opinion> findAll(){
        return new LinkedList<>(opinionRepository.findAll());
    }

    public Long getTotalLikes(Long userId) {
        List<Post> posts = postService.findPostsByUserId(userId);
        Long total = 0L;
        if (!posts.isEmpty()) {
            for (Post post : posts) {
                total += post.getLikes();
            }
        }
        return total;
    }

    public Long getTotalDislikes(Long userId) {
        List<Post> posts = postService.findPostsByUserId(userId);
        Long total = 0L;
        if (!posts.isEmpty()) {
            for (Post post : posts) {
                total += post.getDislikes();
            }
        }
        return total;
    }

    public Long getTotalRating(Long userId) {
        List<Post> posts = postService.findPostsByUserId(userId);
        Long total = 0L;
        if (!posts.isEmpty()) {
            for (Post post : posts) {
                total += post.getRating();
            }
        }
        return total;
    }
}
