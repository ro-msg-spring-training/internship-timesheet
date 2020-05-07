package ro.msg.internship.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.timesheet.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
