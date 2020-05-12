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

@CrossOrigin("*")

@RestController
@RequiredArgsConstructor
public class BookingDetailController {

    private final BookingDetailService bookingDetailService;
    private final PspService pspService;

    @PostMapping(value = "/bookingDetail", produces = "application/json")
    public ResponseEntity<BookingDetailDto> createBookingDetail(@RequestBody BookingDetailDto bookingDetailDto){

        BookingDetail bookingDetail = BookingDetailBuilder.getEntityFromDto(bookingDetailDto);
        bookingDetail.setPsp(pspService.getPspById(bookingDetailDto.getPspId()));
        bookingDetail = bookingDetailService.createBookingDetail(bookingDetail,bookingDetailDto.getDate(),bookingDetailDto.getUserId());

        BookingDetailDto bookingDetailDtoConverted = BookingDetailBuilder
                .getDtoFromEntity(bookingDetail);

        return ResponseEntity.accepted().body(bookingDetailDtoConverted);
    }

    @DeleteMapping(value = "/bookingDetail/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity<BookingDetailDto> deleteBookingDetail(@PathVariable("id") final Integer id) {

        BookingDetail bookingDetail = bookingDetailService.deleteBookingDetail(id);

        BookingDetailDto bookingDetailDto = BookingDetailBuilder.getDtoFromEntity(bookingDetail);

        return ResponseEntity.accepted().body(bookingDetailDto);
    }


}
