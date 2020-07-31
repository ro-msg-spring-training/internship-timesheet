package ro.msg.internship.timesheet.service.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.model.Role;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;
import ro.msg.internship.timesheet.service.UserService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    private User user = new User();

    public void populate() {
        user.setFirstName("Emanuela");
        user.setLastName("Ionas");
        user.setUsername("iema1");
        user.setPassword("iema");
        user.setRole("USER");

        user = userRepository.save(user);
    }

    @Test
    public void testFindByIdSuccess() {

        this.populate();
        System.out.println(userRepository.findAll());

        UserService userService = new UserService(userRepository);

        user = userService.findUserById(user.getUserId());

        Assertions.assertEquals("iema1", user.getUsername());
    }

    @Test
    public void findByIdFail() {
        this.populate();
        UserService userService = new UserService(userRepository);

        Assertions.assertThrows(RuntimeException.class, () -> userService.findUserById(10));
    }

    @Test
    public void testGetUsers() {
        this.populate();
        UserService userService = new UserService(userRepository);

        List<User> users = userService.getUsers();

        Assertions.assertEquals(1, users.size());

    }

    @Test
    public void testFindByUsernameSuccess() {
        this.populate();
        UserService userService = new UserService(userRepository);

        User user = userService.findByUsername("iema1");

        Assertions.assertEquals(userRepository.findAll().get(0).getUserId(), user.getUserId());
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindByUsernameFail() {
        this.populate();
        UserService userService = new UserService(userRepository);

        User user = userService.findByUsername("iemma");

        Assertions.assertNull(user);
    }

    @Test
    public void testCreateUserSuccess(){
        user.setFirstName("Emanuela");
        user.setLastName("Ionas");
        user.setUsername("iema1");
        user.setPassword("iema");
        user.setRole("USER");

        User localUser = userRepository.save(user);
        Assertions.assertEquals(localUser.getFirstName(), user.getFirstName());
    }

    @Before
    @After
    public void clear() {
        userRepository.deleteAll();
    }
}