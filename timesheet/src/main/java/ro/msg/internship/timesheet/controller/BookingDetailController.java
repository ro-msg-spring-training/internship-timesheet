package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.BookingDetailDto;
import ro.msg.internship.timesheet.dto.builder.BookingDetailBuilder;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.service.BookingDetailService;
import ro.msg.internship.timesheet.service.BookingService;
import ro.msg.internship.timesheet.service.PspService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BookingDetailController {
    private final BookingDetailService bookingDetailService;
    private final BookingService bookingService;
    private final PspService pspService;

    @PostMapping(value = "/bookingDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<BookingDetailDto> createBookingDetail(@RequestBody BookingDetailDto bookingDetailDto){

        System.out.println(bookingDetailDto.getBookingId());

        BookingDetail bookingDetail = BookingDetailBuilder.getEntityFromDto(bookingDetailDto);
        bookingDetail.setBooking(bookingService.getBookingById(bookingDetailDto.getBookingId()));
        bookingDetail.setPsp(pspService.getPsp(bookingDetailDto.getPspName()));

        bookingDetail = bookingDetailService.createBookingDetail(
                bookingDetail,
                bookingDetail.getPsp().getName());

        BookingDetailDto bookingDetailDtoConverted = BookingDetailBuilder
                .getDtoFromEntity(bookingDetail, 0.00);

        return ResponseEntity.accepted().body(bookingDetailDtoConverted);
    }

    @DeleteMapping(value = "/bookingDetail/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity<BookingDetailDto> deleteBookingDetail(@PathVariable("id") final Integer id) {

        BookingDetail bookingDetail = bookingDetailService.deleteBookingDetail(id);

        BookingDetailDto bookingDetailDto = BookingDetailBuilder.getDtoFromEntity(bookingDetail,
                0.00);

        return ResponseEntity.accepted().body(bookingDetailDto);
    }

    @GetMapping(value = "/bookingDetail", produces = "application/json")
    public ResponseEntity<List<BookingDetailDto>> getBookingDetail(){

        List<BookingDetailDto> bookingDetailDtosConverted = new ArrayList<>();

        Set<BookingDetail> bookingDetails = bookingDetailService
                .getAllBookingDetail(bookingService.getBookingById(1)).keySet();

        for (BookingDetail bookingDetail : bookingDetails){
            BookingDetailDto bookingDetailDto = BookingDetailBuilder.getDtoFromEntity(bookingDetail, 0.0);
            bookingDetailDto.setBookingId(bookingDetail.getBooking().getBookingId());
            bookingDetailDto.setPspName(bookingDetail.getPsp().getName());

            bookingDetailDtosConverted.add(bookingDetailDto);
        }

        return ResponseEntity.accepted().body(bookingDetailDtosConverted);
    }


}
