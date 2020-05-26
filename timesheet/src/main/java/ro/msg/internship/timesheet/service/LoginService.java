package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.AccessDeniedException;
import ro.msg.internship.timesheet.exception.PasswordNotMatchedException;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User loginUser(User user) {

        System.out.println(user);

        User loggedUser = userRepository.findUserByUsername(user.getUsername()).orElse(null);

        if(loggedUser == null) {
            throw new UserNotFoundException(user.getUsername());
        }

        if(!User.PASSWORD_ENCODER.matches(user.getPassword(), loggedUser.getPassword())){
            throw new PasswordNotMatchedException();
        }

       // ca sa nu folosim dependintele de securitate (pentru testare)
//        if(!loggedUser.getPassword().equals(user.getPassword())) {
//            throw new PasswordNotMatchedException();
//        }


        return loggedUser;

    }

    public User loginAdmin(String username, String password) {
        User loggedUser = userRepository.findUserByUsername(username).orElse(null);

        if(loggedUser == null) {
            throw new UserNotFoundException(username);
        }

        if(!User.PASSWORD_ENCODER.matches(password, loggedUser.getPassword())){
            throw new PasswordNotMatchedException();
        }

        if(loggedUser.getRole().equals("ADMIN")) {
            return loggedUser;
        }

        return null;
    }

    public List<User> loginUser(String username, String password, int appType) {
        User loggedUser = userRepository.findUserByUsername(username).orElse(null);

        if(loggedUser == null) {
            throw new UserNotFoundException(username);
        }

        if(!User.PASSWORD_ENCODER.matches(password, loggedUser.getPassword())){
            throw new PasswordNotMatchedException();
        }

        //pentru aplicatia adminului
        /*if(appType == 1 && loggedUser.getRole().name().equals("USER")) {
            throw new AccessDeniedException();
        }
        if(appType == 0 && loggedUser.getRole().name().equals("ADMIN")) {
                return userRepository.findAll();
        }
        else {
            List<User> users = new ArrayList<>();
            users.add(loggedUser);
            return users;
        }*/
        if(appType == 1 && loggedUser.getRole().equals("USER")) {
            throw new AccessDeniedException();
        }
        if(appType == 0 && loggedUser.getRole().equals("ADMIN")) {
            return userRepository.findAll();
        }
        else {
            List<User> users = new ArrayList<>();
            users.add(loggedUser);
            return users;
        }
    }

}
