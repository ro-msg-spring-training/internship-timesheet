package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.repository.BookingRepository;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking){
        Booking bookingFromDb = bookingRepository.findBookingByDay(booking.getDay()).get(0);
        if (bookingFromDb != null)
            return bookingRepository.save(booking);
        return booking;
    }

    public Booking getBookingById(Integer bookingId){
        return bookingRepository.findById(bookingId).orElse(null);
    }

}
