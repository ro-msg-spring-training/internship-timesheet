package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no user with id: " + id));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findUserByUsername(username).orElse(null);
    }
}
