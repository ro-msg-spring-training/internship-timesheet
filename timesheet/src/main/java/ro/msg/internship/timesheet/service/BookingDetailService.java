package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.BookingDetailRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor
public class BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;
    private final BookingService bookingService;
    private final PspService pspService;

    public Map<BookingDetail, Float> getAllBookingDetail(Booking booking) {
        List<BookingDetail> bookingDetails = bookingDetailRepository.findAllByBooking(booking);
        Map<BookingDetail, Float> bookingDetailAndHour = new HashMap<>();
        for (BookingDetail bookingDetail : bookingDetails) {
            long minutes = MINUTES.between(bookingDetail.getEndHour(), bookingDetail.getStartHour());
            bookingDetailAndHour.put(bookingDetail, (float) (minutes / 60));
        }
        return bookingDetailAndHour;
    }

    public BookingDetail createBookingDetail(BookingDetail bookingDetail, String name) {
        Booking createdBooking = bookingService.getBookingById(bookingDetail.getBooking().getBookingId());
        Psp pspFromDb = pspService.getPsp(name);
        bookingDetail.setPsp(pspFromDb);
        bookingDetail.setBooking(createdBooking);
        return bookingDetailRepository.save(bookingDetail);
    }

    public BookingDetail deleteBookingDetail(Integer id) {
        BookingDetail bookingDetail = bookingDetailRepository.findById(id)
                .orElse(null);
        bookingDetailRepository.deleteById(id);
        return bookingDetail;
    }

}
