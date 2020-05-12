package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User findUserById(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User findByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }
}
