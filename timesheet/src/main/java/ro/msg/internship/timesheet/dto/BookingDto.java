package ro.msg.internship.timesheet.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingDto {

	private Integer bookingId;
	private LocalDate day;
	private String startHour;
    private String endHour;
    private double hours;
    private List<BookingDetailDto> bookingDetails;
	
}
