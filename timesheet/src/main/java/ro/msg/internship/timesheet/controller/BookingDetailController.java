package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.BookingDetailDto;
import ro.msg.internship.timesheet.dto.builder.BookingDetailBuilder;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.service.BookingDetailService;
import ro.msg.internship.timesheet.service.PspService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@CrossOrigin("*")

@RestController
@RequiredArgsConstructor
public class BookingDetailController {

    private final BookingDetailService bookingDetailService;
    private final PspService pspService;

    @PostMapping(value = "/bookingDetail", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<BookingDetailDto> createBookingDetail(@ModelAttribute BookingDetailDto bookingDetailDto) {
        BookingDetail bookingDetail = BookingDetailBuilder.getEntityFromDto(bookingDetailDto);
        bookingDetail.setPsp(pspService.getPspById(bookingDetailDto.getPspId()));
        bookingDetail = bookingDetailService.createBookingDetail(bookingDetail, LocalDate.parse(bookingDetailDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), bookingDetailDto.getUserId());

        BookingDetailDto bookingDetailDtoConverted = BookingDetailBuilder.getDtoFromEntity(bookingDetail);

        return ResponseEntity.accepted().body(bookingDetailDtoConverted);
    }

    @PutMapping(value = "/bookingDetail", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<BookingDetailDto> updateBookingDetail(@ModelAttribute BookingDetailDto bookingDetailDto) {
        BookingDetail bookingDetail = BookingDetailBuilder.getEntityFromDto(bookingDetailDto);
        bookingDetail.setPsp(pspService.getPspById(bookingDetailDto.getPspId()));

        BookingDetail updatedBookingDetail = bookingDetailService.updateBookingDetail(bookingDetail);

        return ResponseEntity.accepted().body(BookingDetailBuilder.getDtoFromEntity(updatedBookingDetail));
    }

    @DeleteMapping(value = "/bookingDetail/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity<BookingDetailDto> deleteBookingDetail(@PathVariable("id") final Integer id) {
        BookingDetail bookingDetail = bookingDetailService.deleteBookingDetail(id);

        BookingDetailDto bookingDetailDto = BookingDetailBuilder.getDtoFromEntity(bookingDetail);

        return ResponseEntity.accepted().body(bookingDetailDto);
    }

}
