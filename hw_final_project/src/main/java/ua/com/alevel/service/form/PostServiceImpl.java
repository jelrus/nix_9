package ua.com.alevel.service.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.form.PostRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final BaseCrudRepository<Post, PostRepository> basePostRepository;
    private final UserRepository userRepository;
    private final BaseCrudRepository<User, UserRepository> baseUserRepository;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public PostServiceImpl(PostRepository postRepository,
                           BaseCrudRepository<Post, PostRepository> basePostRepository,
                           UserRepository userRepository,
                           BaseCrudRepository<User, UserRepository> baseUserRepository) {
        this.postRepository = postRepository;
        this.basePostRepository = basePostRepository;
        this.userRepository = userRepository;
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Post post) {
        LOGGER_INFO.info("Post creating has been started");
        post.setLikes(0L);
        post.setDislikes(0L);
        post.setRating(0L);
        basePostRepository.create(postRepository, post);
        LOGGER_INFO.info("Post [" + post.getId() + "] has been created");
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Post post) {
        LOGGER_INFO.info("Post [" + post.getId() + "] updating has been started");
        post.setLikes(post.getLikes());
        post.setDislikes(post.getDislikes());
        post.setRating(post.getRating());
        basePostRepository.update(postRepository, post);
        LOGGER_INFO.info("Post [" + post.getId() + "] has been updated");
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        LOGGER_WARNING.warn("Post [" + id + "] deleting started");
        basePostRepository.delete(postRepository, id);
        LOGGER_WARNING.warn("Post [" + id + "] has been deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return basePostRepository.findById(postRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Post> findAll(DataTableRequest request) {
        return basePostRepository.findAll(postRepository, request);
    }

    @Override
    public List<Post> findPostsFiltered() {
        return postRepository.findByVisibleTrueOrderByCreatedDesc();
    }

    @Override
    public List<Post> findPostsByUserId(Long userId) {
        return postRepository.findByVisibleTrueAndUserIdOrderByCreatedDesc(userId);
    }

    @Override
    public User findUserByPostId(Long postId) {
        return baseUserRepository.findById(userRepository, basePostRepository
                                 .findById(postRepository, postId).get()
                                 .getUser().getId()).get();
    }
}
