package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.PasswordNotMatchedException;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User loginUser(User user) {

        User loggedUser = userRepository.findUserByUsername(user.getUsername()).orElse(null);

        if(loggedUser == null) {
            throw new UserNotFoundException(user.getUsername());
        }

        if(!User.PASSWORD_ENCODER.matches(user.getPassword(), loggedUser.getPassword())){
            throw new PasswordNotMatchedException();
        }

        /*ca sa nu folosim dependintele de securitate (pentru testare)*/
//        if(!loggedUser.getPassword().equals(user.getPassword())) {
//            throw new PasswordNotMatchedException();
//        }


        return loggedUser;

    }

    public User loginAdmin(User user) {
        User loggedUser = userRepository.findUserByUsername(user.getUsername()).orElse(null);

        if(loggedUser == null) {
            throw new UserNotFoundException(user.getUsername());
        }

        if(!User.PASSWORD_ENCODER.matches(user.getPassword(), loggedUser.getPassword())){
            throw new PasswordNotMatchedException();
        }

        if(loggedUser.getRole().name().equals("ADMIN")) {
            return loggedUser;
        }

        return null;
    }

}
