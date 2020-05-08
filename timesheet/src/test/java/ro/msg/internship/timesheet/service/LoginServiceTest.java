package ro.msg.internship.timesheet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.msg.internship.timesheet.exception.PasswordNotMatchedException;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;

    @Test
    void passTest() {
        User userEncode = User.builder().userId(1).username("a").build();
        String password = new BCryptPasswordEncoder().encode("a");
        userEncode.setPassword(password);

        User user = User.builder().userId(1).username("a").password("a").build();
        when(userRepository.findUserByUsername("a")).thenReturn(java.util.Optional.ofNullable(userEncode));

        User loggedUser = loginService.loginUser(user);
        assertNotNull(loggedUser);
        assertEquals(userEncode, loggedUser);
    }

    @Test
    void userNotFound() {
        User user = User.builder().userId(1).build();
        assertThrows(UserNotFoundException.class, () -> {loginService.loginUser(user);});
    }

    @Test
    void passwordIncorect() {
        User userEncode = User.builder().userId(1).username("a").build();
        String password = new BCryptPasswordEncoder().encode("a");
        userEncode.setPassword(password);

        User user = User.builder().userId(1).username("a").password("b").build();
        when(userRepository.findUserByUsername("a")).thenReturn(java.util.Optional.ofNullable(userEncode));

        assertThrows(PasswordNotMatchedException.class, () -> {loginService.loginUser(user);});
    }

}