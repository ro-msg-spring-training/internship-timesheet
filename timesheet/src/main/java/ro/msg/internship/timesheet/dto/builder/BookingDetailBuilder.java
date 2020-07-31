package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.BookingDetailDto;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.model.Status;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MINUTES;

public class BookingDetailBuilder {
    public static BookingDetailDto getDtoFromEntity(BookingDetail bookingDetail) {
        return BookingDetailDto.builder()
                .id(bookingDetail.getBookingDetailId())
                .description(bookingDetail.getDescription())
                .endHour(bookingDetail.getEndHour().toString())
                .pspName(bookingDetail.getPsp().getName())
                .hours(getHour(bookingDetail))
                .startHour(bookingDetail.getStartHour().toString())
                .status(bookingDetail.getStatus().toString())
                .day(bookingDetail.getBooking().getDay().toString())
                .bookingId(bookingDetail.getBooking().getBookingId())
                .userId(bookingDetail.getBooking().getUser().getUserId())
                .pspId(bookingDetail.getPsp().getPspId())
                .build();
    }

    public static BookingDetail getEntityFromDto(BookingDetailDto bookingDetailDto) {

        return BookingDetail.builder()
                .bookingDetailId(bookingDetailDto.getId())
                .status(Status.CREATED)
                .description(bookingDetailDto.getDescription())
                .endHour(LocalTime.parse(bookingDetailDto.getEndHour(), DateTimeFormatter.ofPattern("HH:mm")))
                .startHour(LocalTime.parse(bookingDetailDto.getStartHour(), DateTimeFormatter.ofPattern("HH:mm")))
                .build();
    }

    private static double getHour(BookingDetail bookingDetail) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Double.valueOf(decimalFormat.format(MINUTES.between(bookingDetail.getStartHour(), bookingDetail.getEndHour()) / 60.0));
    }
}
