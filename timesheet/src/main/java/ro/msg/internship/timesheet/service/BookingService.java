package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.BookingNotFoundException;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.BookingRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;

    public Booking getOrCreateBooking(Booking booking) {
        Optional<Booking> bookingFromDb = bookingRepository.findBookingByDayAndUser(booking.getDay(), booking.getUser());
        return bookingFromDb.orElseGet(() -> bookingRepository.save(booking));
    }

    @Transactional
    public Booking getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(BookingNotFoundException::new);
    }

    public List<Booking> getBookingsByUserId(Integer userId) {
        User user = userService.findUserById(userId);
        return bookingRepository.findAllByUser(user);
    }

    @Transactional
    public void deleteAll(){
        bookingRepository.deleteAll();
    }

}
