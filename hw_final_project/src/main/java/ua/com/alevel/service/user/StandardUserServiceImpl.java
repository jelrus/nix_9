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
import ua.com.alevel.persistence.entity.user.StandardUser;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.form.CommentRepository;
import ua.com.alevel.persistence.repository.form.PostRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.StandardUserService;

import java.util.Optional;

@Service
public class StandardUserServiceImpl implements StandardUserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BaseCrudRepository<User, UserRepository> baseUserRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BaseCrudRepository<Post, PostRepository> basePostRepository;
    private final CommentRepository commentRepository;
    private final BaseCrudRepository<Comment, CommentRepository> baseCommentRepository;

    public StandardUserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                                   BaseCrudRepository<User, UserRepository> baseUserRepository,
                                   UserRepository userRepository, PostRepository postRepository,
                                   BaseCrudRepository<Post, PostRepository> basePostRepository,
                                   CommentRepository commentRepository, BaseCrudRepository<Comment,
                                   CommentRepository> baseCommentRepository) {
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
    public void create(User standardUser) {
        if (userRepository.existsByUsername(standardUser.getUsername())) {
            throw new EntityExistException("this user is exist");
        }
        standardUser.setPassword(bCryptPasswordEncoder.encode(standardUser.getPassword()));
        baseUserRepository.create(userRepository, standardUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(User standardUser) {
        baseUserRepository.update(userRepository, standardUser);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        baseUserRepository.delete(userRepository, id);
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
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        post.setUser(user);
        basePostRepository.create(postRepository, post);
        user.getPosts().add(post);
        baseUserRepository.update(userRepository, user);
    }

    @Override
    public void deletePost(String username, Long postId) {
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        Post post = basePostRepository.findById(postRepository, postId).get();
        user.getPosts().remove(post);
        basePostRepository.delete(postRepository, postId);
    }

    @Override
    public void createComment(String username, Long postId, Comment comment) {
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        Post post = basePostRepository.findById(postRepository, postId).get();
        comment.setPost(post);
        comment.setUser(user);
        baseCommentRepository.create(commentRepository, comment);
        user.getComments().add(comment);
        post.getComments().add(comment);
        basePostRepository.update(postRepository, post);
        baseUserRepository.update(userRepository, user);
    }

    @Override
    public void deleteComment(String username, Long postId, Long commentId) {
        User user = baseUserRepository.findById(userRepository, getIdByUsername(username)).get();
        Post post = basePostRepository.findById(postRepository, postId).get();
        Comment comment = baseCommentRepository.findById(commentRepository, commentId).get();
        user.getComments().remove(comment);
        post.getComments().remove(comment);
        baseCommentRepository.delete(commentRepository, commentId);
    }
}
