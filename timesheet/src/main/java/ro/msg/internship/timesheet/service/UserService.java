package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.exception.UsernameFoundException;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public User createUser(User user) {
        Optional<User> checkedUser = userRepository.findUserByUsername(user.getUsername());
        if (!checkedUser.isPresent())
            return userRepository.save(user);
        throw new UsernameFoundException(user.getUsername());
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
}
