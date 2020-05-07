package ro.msg.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.model.Booking;
import ro.msg.internship.model.BookingDetail;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {
    List<BookingDetail> findAllByBooking(Booking booking);
}
