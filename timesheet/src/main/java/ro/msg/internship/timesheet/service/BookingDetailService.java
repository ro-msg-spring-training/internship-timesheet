package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.BookingDetailNotFoundException;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.BookingDetailRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

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
                .orElseThrow(BookingDetailNotFoundException::new);
        bookingDetailRepository.deleteById(id);
        return bookingDetail;
    }

    public BookingDetail getBookingDetailsById(Integer id){
        return bookingDetailRepository.findById(id).orElseThrow(BookingDetailNotFoundException::new);
    }

    public List<BookingDetail> getBookingDetails(){
        return bookingDetailRepository.findAll();
    }

    public void deleteAll(){
        bookingDetailRepository.deleteAll();
    }

}
