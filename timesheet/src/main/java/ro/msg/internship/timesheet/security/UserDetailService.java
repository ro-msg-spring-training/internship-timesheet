package ro.msg.internship.timesheet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.service.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetail loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " was not found");
        }

        return new UserDetail(user);
        /*return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.NO_AUTHORITIES
        );*/
    }
}
