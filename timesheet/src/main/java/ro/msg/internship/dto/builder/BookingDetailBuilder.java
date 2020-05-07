package ro.msg.internship.dto.builder;

import ro.msg.internship.dto.BookingDetailDto;
import ro.msg.internship.model.BookingDetail;

public class BookingDetailBuilder {
    public static BookingDetailDto getDtoFromEntity(BookingDetail bookingDetail, Float hour) {
        return BookingDetailDto.builder()
                .description(bookingDetail.getDescription())
                .endHour(bookingDetail.getEndHour())
                .pspName(bookingDetail.getPsp().getName())
                .hours(hour)
                .startHour(bookingDetail.getStartHour())
                .status(bookingDetail.getStatus().toString())
                .build();
    }

    public static BookingDetail getEntityFromDto(BookingDetail bookingDetail){
        return BookingDetail.builder()
                        .status(bookingDetail.getStatus())
                        .description(bookingDetail.getDescription())
                        .endHour(bookingDetail.getEndHour())
                        .startHour(bookingDetail.getStartHour())
                        .bookingDetailId(bookingDetail.getBookingDetailId())
                        .build();
    }
}
