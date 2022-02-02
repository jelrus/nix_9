package ua.com.alevel.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.form.CommentRepository;
import ua.com.alevel.persistence.repository.form.PostRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BaseCrudRepository<User, UserRepository> baseUserRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BaseCrudRepository<Post, PostRepository> basePostRepository;
    private final CommentRepository commentRepository;
    private final BaseCrudRepository<Comment, CommentRepository> baseCommentRepository;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public AdminServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                            BaseCrudRepository<User, UserRepository> baseUserRepository,
                            UserRepository userRepository, PostRepository postRepository,
                            BaseCrudRepository<Post, PostRepository> basePostRepository,
                            CommentRepository commentRepository,
                            BaseCrudRepository<Comment, CommentRepository> baseCommentRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.baseUserRepository = baseUserRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.basePostRepository = basePostRepository;
        this.commentRepository = commentRepository;
        this.baseCommentRepository = baseCommentRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(User admin) {
        LOGGER_INFO.info("Admin creating has been started");
        if (userRepository.existsByUsername(admin.getUsername())) {
            LOGGER_ERROR.error("User [" + admin.getId() + "] is already exist");
            throw new EntityExistException("this user is exist");
        }
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        baseUserRepository.create(userRepository, admin);
        LOGGER_INFO.info("Admin [" + admin.getId() + "] has been created");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(User admin) {
        LOGGER_INFO.info("Admin [" + admin.getId() + "] updating has been started");
        baseUserRepository.update(userRepository, admin);
        LOGGER_INFO.info("Admin [" + admin.getId() + "] updating has been completed");
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        LOGGER_WARNING.warn("Admin [" + id +"] deleting has been started");
        baseUserRepository.delete(userRepository, id);
        LOGGER_WARNING.warn("Admin [" + id + "] has been deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return baseUserRepository.findById(userRepository, id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return baseUserRepository.findAll(userRepository, request);
    }

    @Override
    public Long getIdByUsername(String username) {
        return userRepository.findByUsername(username).getId();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createPost(String username, Post post) {
        LOGGER_INFO.info("Post creating by User [" + username + "] has been started");
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        post.setUser(user);
        basePostRepository.create(postRepository, post);
        user.getPosts().add(post);
        baseUserRepository.update(userRepository, user);
        LOGGER_INFO.info("Post [" + post.getId() + "] by User [" + username + "] has been created");
    }

    @Override
    public void deletePost(String username, Long postId) {
        LOGGER_WARNING.warn("Post [" + postId + "] deleting by User [" + username + "] has been started");
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        Post post = basePostRepository.findById(postRepository, postId).get();
        user.getPosts().remove(post);
        basePostRepository.delete(postRepository, postId);
        LOGGER_WARNING.warn("Post [" + postId + "] by User [" + username + "] has been removed");
    }

    @Override
    public void createComment(String username, Long commentId, Comment comment) {
        LOGGER_INFO.info("Comment [" + commentId + "] by User [" + username + "] creating has been started");
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        Post post = basePostRepository.findById(postRepository, commentId).get();
        comment.setPost(post);
        comment.setUser(user);
        baseCommentRepository.create(commentRepository, comment);
        user.getComments().add(comment);
        post.getComments().add(comment);
        basePostRepository.update(postRepository, post);
        baseUserRepository.update(userRepository, user);
        LOGGER_INFO.info("Comment [" + commentId + "] by User [" + username + "] has been created");
    }

    @Override
    public void deleteComment(String username, Long postId, Long commentId) {
        LOGGER_WARNING.warn("Comment [" + commentId + "] by User [" + username + "] deleting has been started");
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        Post post = basePostRepository.findById(postRepository, postId).get();
        Comment comment = baseCommentRepository.findById(commentRepository, commentId).get();
        user.getComments().remove(comment);
        post.getComments().remove(comment);
        baseCommentRepository.delete(commentRepository, commentId);
        LOGGER_WARNING.warn("Comment [" + commentId + "] by User [" + username + "] has been deleted");
    }

    @Override
    public void disablePost(Long postId) {
        LOGGER_WARNING.warn("Post [" + postId + "] disabling has been started");
        Post post = basePostRepository.findById(postRepository, postId).get();
        post.setVisible(!post.getVisible().equals(true));
        basePostRepository.update(postRepository, post);
        LOGGER_WARNING.warn("Comment [" + postId + "] has been disabled");
    }

    @Override
    public void disableComment(Long commentId) {
        LOGGER_WARNING.warn("Post [" + commentId + "] disabling has been started");
        Comment comment = baseCommentRepository.findById(commentRepository, commentId).get();
        comment.setVisible(!comment.getVisible().equals(true));
        baseCommentRepository.update(commentRepository, comment);
        LOGGER_WARNING.warn("Comment [" + commentId + "] has been disabled");
    }

    @Override
    public void disableUser(Long userId) {
        LOGGER_WARNING.warn("User [" + userId + "] disabling has been started");
        User user = baseUserRepository.findById(userRepository, userId).get();
        user.setEnabled(!user.getEnabled().equals(true));
        baseUserRepository.update(userRepository, user);
        LOGGER_WARNING.warn("User [" + userId + "] has been disabled");
    }

    @Override
    public Integer getGlobalPosts() {
        return postRepository.findAll().size();
    }

    @Override
    public Integer getGlobalComments() {
        return commentRepository.findAll().size();
    }

    @Override
    public Integer getGlobalLikes() {
        int globalLikes = 0;
        List<Post> posts = postRepository.findAll();
        for (Post post: posts) {
            globalLikes += post.getLikes().intValue();
        }
        return globalLikes;
    }

    @Override
    public Integer getGlobalDislikes() {
        int globalDislikes = 0;
        List<Post> posts = postRepository.findAll();
        for (Post post: posts) {
            globalDislikes += post.getDislikes().intValue();
        }
        return globalDislikes;
    }
}
