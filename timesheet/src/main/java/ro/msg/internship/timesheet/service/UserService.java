package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.FirstNameUserException;
import ro.msg.internship.timesheet.exception.LastNameUserException;
import ro.msg.internship.timesheet.exception.UserNotFoundException;
import ro.msg.internship.timesheet.exception.UsernameFoundException;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public User createUser(User user){
        if (!user.getFirstName().matches("^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$")) {
            throw new FirstNameUserException("FirstName Has to contain only letters or one space and ' or -");
        }
        if (!user.getLastName().matches("^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$")) {
            throw new LastNameUserException("LastName Has to contain only letters or one space and ' or -");
        }
        Optional<User> checkedUser = userRepository.findUserByUsername(user.getUsername());
        if (!checkedUser.isPresent())
            return userRepository.save(user);
        throw new UsernameFoundException(user.getUsername());
    }

    public List<User> createAll (Set<User> users){
        List<User> output = new ArrayList<>();
        for(User user: users){
            output.add(createUser(user));
        }
        return output;
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }
}
