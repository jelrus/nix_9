package ua.com.alevel.service.form;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.form.Comment;
import ua.com.alevel.persistence.entity.form.Post;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.form.CommentRepository;
import ua.com.alevel.persistence.repository.form.PostRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    private static final Long FIRST = 1L;
    private static final String FIRST_BODY = "I am first comment!";
    private static final Long SECOND = 2L;
    private static final String SECOND_BODY = "I am second comment!";

    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private BaseCrudRepository<Comment, CommentRepository> baseCommentRepository;

    @Test
    public void testFindById() {
        //given
        Comment firstComment = prepareData(FIRST, FIRST_BODY, true);
        Comment secondComment = prepareData(SECOND, SECOND_BODY, false);

        //when
        when(baseCommentRepository.findById(commentRepository, 1L)).thenReturn(Optional.of(firstComment));
        when(baseCommentRepository.findById(commentRepository, 2L)).thenReturn(Optional.of(secondComment));

        //then
        Comment toFindFirst = commentService.findById(FIRST).get();
        Comment toFindSecond = commentService.findById(SECOND).get();

        assertEquals(toFindFirst, firstComment);
        assertEquals(toFindSecond, secondComment);

        assertNotEquals(toFindFirst, secondComment);
        assertNotEquals(toFindSecond, firstComment);
    }

    private Comment prepareData(long id, String body, boolean visible) {
        Comment result = new Comment();
        result.setId(id);
        result.setBody(body);
        result.setVisible(visible);
        return result;
    }


}
