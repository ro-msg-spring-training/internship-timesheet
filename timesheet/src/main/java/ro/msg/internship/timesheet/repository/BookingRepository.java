package ro.msg.internship.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
    Optional<Booking> findBookingByDayAndUser(LocalDate day, User user);

    List<Booking> findAllByUser(User user);
}
