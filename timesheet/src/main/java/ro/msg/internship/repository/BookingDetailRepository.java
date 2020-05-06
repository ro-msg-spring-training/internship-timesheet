package ro.msg.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.internship.model.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {

}
