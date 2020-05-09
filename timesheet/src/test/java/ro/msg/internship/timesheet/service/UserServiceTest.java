package ro.msg.internship.timesheet.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByIdSuccess() {

        UserService userService = new UserService(userRepository);

        User user = userService.findUserById(4);

        Assertions.assertEquals("iema",user.getUsername());
    }

    @Test
    void findByIdFail() {

        UserService userService = new UserService(userRepository);

        Assertions.assertThrows(RuntimeException.class, () -> userService.findUserById(10));
    }

    @Test
    void testGetUsers() {

        UserService userService = new UserService(userRepository);

        List<User> users = userService.getUsers();

        Assertions.assertEquals(7, users.size());

    }

    @Test
    void testFindByUsernameSuccess() {

        UserService userService = new UserService(userRepository);

        User user = userService.findByUsername("iema");

        Assertions.assertEquals(4, user.getUserId());
    }

    @Test
    void testFindByUsernameFail() {

        UserService userService = new UserService(userRepository);

        User user = userService.findByUsername("iemma");

        Assertions.assertNull(user);
    }

}