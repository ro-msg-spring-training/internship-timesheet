package ro.msg.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
