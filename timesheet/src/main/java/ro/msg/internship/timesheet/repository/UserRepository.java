package ro.msg.internship.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.timesheet.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findUserByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
