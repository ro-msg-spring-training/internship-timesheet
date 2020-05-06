package ro.msg.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
