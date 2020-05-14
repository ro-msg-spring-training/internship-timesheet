package ro.msg.internship.timesheet.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingDetailDto {

    private Integer id;
    private String description;
    private String startHour;
    private String endHour;
    private String status;
    private double hours;
    private String pspName;
    private String date;
    private Integer bookingId;
    private Integer pspId;
    private Integer userId;

}
