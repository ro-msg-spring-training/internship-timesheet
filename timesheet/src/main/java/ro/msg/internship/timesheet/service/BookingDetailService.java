package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.repository.BookingDetailRepository;

import static java.time.temporal.ChronoUnit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;

    public Map<BookingDetail, Float> getAllBookingDetail(Booking booking){
        List<BookingDetail> bookingDetails = bookingDetailRepository.findAllByBooking(booking);
        Map<BookingDetail, Float> bookingDetailAndHour = new HashMap<>();
        for(BookingDetail bookingDetail: bookingDetails){
            long minutes = MINUTES.between(bookingDetail.getEndHour(), bookingDetail.getStartHour()) ;
            bookingDetailAndHour.put(bookingDetail, (float) (minutes / 60));
        }
        return bookingDetailAndHour;
    }

}
