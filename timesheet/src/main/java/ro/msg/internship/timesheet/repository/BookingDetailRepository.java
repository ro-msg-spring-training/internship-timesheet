package ro.msg.internship.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {

    List<BookingDetail> findAllByBooking(Booking booking);
}
