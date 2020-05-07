package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.BookingDetailDto;
import ro.msg.internship.timesheet.model.BookingDetail;
import ro.msg.internship.timesheet.model.Status;

public class BookingDetailBuilder {
    public static BookingDetailDto getDtoFromEntity(BookingDetail bookingDetail, double hour) {
        return BookingDetailDto.builder()
                .id(bookingDetail.getBookingDetailId())
                .description(bookingDetail.getDescription())
                .endHour(bookingDetail.getEndHour())
                .pspName(bookingDetail.getPsp().getName())
                .hours(hour)
                .startHour(bookingDetail.getStartHour())
                .status(bookingDetail.getStatus().toString())
                .date(bookingDetail.getBooking().getDay())
                .bookingId(bookingDetail.getBooking().getBookingId())
                .build();
    }

    public static BookingDetail getEntityFromDto(BookingDetailDto bookingDetailDto){

        return BookingDetail.builder()
                        .bookingDetailId(bookingDetailDto.getId())
                        .status(Status.CREATED)
                        .description(bookingDetailDto.getDescription())
                        .endHour(bookingDetailDto.getEndHour())
                        .startHour(bookingDetailDto.getStartHour())
                        .build();
    }
}
