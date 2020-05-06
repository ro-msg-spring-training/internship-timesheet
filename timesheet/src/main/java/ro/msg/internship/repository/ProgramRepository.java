package ro.msg.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

}
