package ua.com.alevel.service.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.form.CommentRepository;
import ua.com.alevel.persistence.repository.form.PostRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BaseCrudRepository<Comment, CommentRepository> baseCommentRepository;
    private final UserRepository userRepository;
    private final BaseCrudRepository<User, UserRepository> baseUserRepository;
    private final PostRepository postRepository;
    private final BaseCrudRepository<Post, PostRepository> basePostRepository;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARNING = LoggerFactory.getLogger("warn");

    public CommentServiceImpl(CommentRepository commentRepository,
                              BaseCrudRepository<Comment, CommentRepository> baseCommentRepository,
                              UserRepository userRepository,
                              BaseCrudRepository<User, UserRepository> baseUserRepository,
                              PostRepository postRepository,
                              BaseCrudRepository<Post, PostRepository> basePostRepository) {
        this.commentRepository = commentRepository;
        this.baseCommentRepository = baseCommentRepository;
        this.userRepository = userRepository;
        this.baseUserRepository = baseUserRepository;
        this.postRepository = postRepository;
        this.basePostRepository = basePostRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Comment comment) {
        LOGGER_INFO.info("Comment creating has been started");
        baseCommentRepository.create(commentRepository, comment);
        LOGGER_INFO.info("Comment [" + comment.getId() + "] has been created");
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Comment comment) {
        LOGGER_INFO.info("Comment [" + comment.getId() + "] updating has been started");
        baseCommentRepository.update(commentRepository, comment);
        LOGGER_INFO.info("Comment [" + comment.getId() + "] has been updated");
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        LOGGER_WARNING.warn("Comment [" + id + "] deleting started");
        baseCommentRepository.delete(commentRepository, id);
        LOGGER_WARNING.warn("Comment [" + id + "] has been deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) {
        return baseCommentRepository.findById(commentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Comment> findAll(DataTableRequest request) {
        return baseCommentRepository.findAll(commentRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsFiltered() {
        return commentRepository.findByVisibleTrueOrderByCreatedDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByUserId(Long userId) {
        return commentRepository.findByVisibleTrueAndUserIdOrderByCreatedDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findByVisibleTrueAndPostIdOrderByCreatedDesc(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByCommentId(Long commentId) {
        return baseUserRepository.findById(userRepository, baseCommentRepository
                                 .findById(commentRepository, commentId).get()
                                 .getUser().getId()).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Post findPostByCommentId(Long commentId) {
        return basePostRepository.findById(postRepository, baseCommentRepository
                                 .findById(commentRepository, commentId).get()
                                 .getPost().getId()).get();
    }
}
