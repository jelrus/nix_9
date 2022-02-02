package ua.com.alevel.service.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.form.CommentRepository;
import ua.com.alevel.persistence.repository.form.PostRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    private static final Long FIRST = 1L;
    private static final String FIRST_TITLE = "First title";
    private static final String FIRST_BODY = "I am first post!";
    private static final Long SECOND = 2L;
    private static final String SECOND_TITLE = "Second title";
    private static final String SECOND_BODY = "I am second post!";

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;
    @Mock
    private BaseCrudRepository<Post, PostRepository> baseCommentRepository;

    @Test
    public void testFindById() {
        //given
        Post firstPost = prepareData(FIRST, FIRST_TITLE, FIRST_BODY, true);
        Post secondPost = prepareData(SECOND, SECOND_TITLE, SECOND_BODY, false);

        //when
        when(baseCommentRepository.findById(postRepository, 1L)).thenReturn(Optional.of(firstPost));
        when(baseCommentRepository.findById(postRepository, 2L)).thenReturn(Optional.of(secondPost));

        //then
        Post toFindFirst = postService.findById(FIRST).get();
        Post toFindSecond = postService.findById(SECOND).get();

        assertEquals(toFindFirst, firstPost);
        assertEquals(toFindSecond, secondPost);

        assertNotEquals(toFindFirst, secondPost);
        assertNotEquals(toFindSecond, firstPost);
    }

    private Post prepareData(Long id, String title, String body, boolean visible) {
        Post result = new Post();
        result.setId(id);
        result.setTitle(title);
        result.setBody(body);
        result.setVisible(visible);
        return result;
    }
}
