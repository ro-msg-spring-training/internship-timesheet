package ro.msg.internship.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.timesheet.model.Psp;

import java.util.List;

public interface PspRepository extends JpaRepository<Psp, Integer> {

    List<Psp> findPspByName(String name);

}
