package ro.msg.internship.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.timesheet.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
