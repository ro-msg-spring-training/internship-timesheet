package ro.msg.internship.timesheet.dto;

import lombok.*;

import java.time.LocalDate;
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
    private double hours;
    private String pspName;
    private LocalDate date;
    private Integer bookingId;
    private Integer pspId;
    private Integer userId;

}
