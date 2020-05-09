package ro.msg.internship.timesheet.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.model.Role;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    private User user = new User();

    @Before
    public void init() {
        user.setFirstName("Emanuela");
        user.setLastName("Ionas");
        user.setUsername("iema");
        user.setPassword("iema");
        user.setRole(Role.USER);
        user.setUserId(1);

        user = userRepository.save(user);
    }

    @Test
    public void testFindByIdSuccess() {

        UserService userService = new UserService(userRepository);

        user = userService.findUserById(user.getUserId());

        Assertions.assertEquals("iema", user.getUsername());
    }

    @Test
    public void findByIdFail() {

        UserService userService = new UserService(userRepository);

        Assertions.assertThrows(RuntimeException.class, () -> userService.findUserById(10));
    }

    @Test
    public void testGetUsers() {

        UserService userService = new UserService(userRepository);

        List<User> users = userService.getUsers();

        Assertions.assertEquals(1, users.size());

    }

    @Test
    public void testFindByUsernameSuccess() {

        UserService userService = new UserService(userRepository);

        User user = userService.findByUsername("iema");

        Assertions.assertEquals(userRepository.findAll().get(0).getUserId(), user.getUserId());
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindByUsernameFail() {

        UserService userService = new UserService(userRepository);

        User user = userService.findByUsername("iemma");

        Assertions.assertNull(user);
    }

    @After
    public void clear() {
        userRepository.deleteAll();
    }
}