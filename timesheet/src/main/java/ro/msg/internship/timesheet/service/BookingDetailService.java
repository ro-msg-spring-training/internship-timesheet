package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.BookingDetailRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;
    private final BookingService bookingService;
    private final UserService userService;

    @Transactional
    public BookingDetail createBookingDetail(BookingDetail bookingDetail, LocalDate day, Integer userId) {
        User user = userService.findUserById(userId);
        Booking booking = Booking.builder().day(day).user(user).build();
        Booking bookingInDb = bookingService.getOrCreateBooking(booking);
        bookingDetail.setBooking(bookingInDb);

        return bookingDetailRepository.save(bookingDetail);
    }

    public BookingDetail deleteBookingDetail(Integer id) {
        BookingDetail bookingDetail = bookingDetailRepository.findById(id)
                .orElse(null);
        bookingDetailRepository.deleteById(id);
        return bookingDetail;
    }

}
