package ua.com.alevel.service.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.alevel.persistence.crud.BaseCrudRepository;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.StandardUser;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StandardUserServiceImplTest {

    private static final Long FIRST = 1L;
    private static final String FIRST_USERNAME = "first username";
    private static final String FIRST_PASSWORD = "first password";
    private static final String FIRST_NAME = "first name";
    private static final String FIRST_LAST_NAME = "first last name";
    private static final String FIRST_DESCRIPTION = "first description";
    private static final Long SECOND = 2L;
    private static final String SECOND_USERNAME = "second username";
    private static final String SECOND_PASSWORD = "second password";
    private static final String SECOND_NAME = "second name";
    private static final String SECOND_LAST_NAME = "second last name";
    private static final String SECOND_DESCRIPTION = "second description";

    @InjectMocks
    StandardUserServiceImpl standardUserService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BaseCrudRepository<User, UserRepository> baseUserRepository;

    @Test
    public void testFindById() {
        //given
        User firstUser = prepareData(FIRST, true, true, FIRST_USERNAME, FIRST_PASSWORD,
                FIRST_NAME, FIRST_LAST_NAME, FIRST_DESCRIPTION);
        User secondUser = prepareData(SECOND, true, true, SECOND_USERNAME, SECOND_PASSWORD,
                SECOND_NAME, SECOND_LAST_NAME, SECOND_DESCRIPTION);

        //when
        when(baseUserRepository.findById(userRepository, 1L)).thenReturn(Optional.of(firstUser));
        when(baseUserRepository.findById(userRepository, 2L)).thenReturn(Optional.of(secondUser));

        //then
        User toFindFirst = standardUserService.findById(FIRST).get();
        User toFindSecond = standardUserService.findById(SECOND).get();

        assertEquals(toFindFirst, firstUser);
        assertEquals(toFindSecond, secondUser);

        assertNotEquals(toFindFirst, secondUser);
        assertNotEquals(toFindSecond, firstUser);
    }

    private StandardUser prepareData(long id, boolean visible, boolean enabled, String username, String password,
                                     String firstName, String lastName, String description) {
        StandardUser result = new StandardUser();
        result.setId(id);
        result.setVisible(visible);
        result.setEnabled(enabled);
        result.setUsername(username);
        result.setPassword(password);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setDescription(description);
        return result;
    }
}
