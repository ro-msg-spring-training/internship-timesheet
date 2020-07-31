package ro.msg.internship.timesheet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.timesheet.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
	
	Optional<Program> findByName(String name);

}
