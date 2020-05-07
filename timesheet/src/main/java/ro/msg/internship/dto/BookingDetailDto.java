package ro.msg.internship.dto;

import lombok.*;
import ro.msg.internship.model.BookingDetail;

import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingDetailDto {

    private Integer id;
    private String description;
    private LocalTime startHour;
    private LocalTime endHour;
    private String status;
    private Float hours;
    private String pspName;
}
