package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.internship.timesheet.dto.BookingDto;
import ro.msg.internship.timesheet.dto.builder.BookingBuilder;
import ro.msg.internship.timesheet.service.BookingService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/bookings/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsForUser(@PathVariable Integer userId) {
        List<BookingDto> bookings = new ArrayList<>();

        bookingService.getBookingsByUserId(userId).forEach(bookingEntity -> bookings.add(BookingBuilder.getDtoFromEntity(bookingEntity)));
        return ResponseEntity.accepted().body(bookings);
    }

}
