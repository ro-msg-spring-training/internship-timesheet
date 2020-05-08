package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.repository.BookingRepository;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;

    public Booking getOrCreateBooking(Booking booking) {
        Optional<Booking> bookingFromDb = bookingRepository.findBookingByDayAndUser(booking.getDay(), booking.getUser());
        return bookingFromDb.orElseGet(() -> bookingRepository.save(booking));
    }

    public Booking getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    public List<Booking> getBookingsByUserId(Integer userId) {
        User user = userService.findUserById(userId);
        return bookingRepository.findAllByUser(user);
    }

}
