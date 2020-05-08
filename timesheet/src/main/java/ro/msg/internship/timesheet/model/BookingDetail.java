package ro.msg.internship.timesheet.model;

import java.time.LocalTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "BookingDetail")
@Table(name = "booking_detail", schema = "timesheet")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetail {

	@Id
	@Column(name = "booking_detail_id", nullable = false, unique = true, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingDetailId;
	
	@Column(name = "start_hour", nullable = false)
	private LocalTime startHour;

	@Column(name = "end_hour", nullable = false)
	private LocalTime endHour;

	@Column(name = "description")
	private String description;
	
	@Column(name = "status", nullable = false, columnDefinition = "int")
	@Enumerated
    private Status status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
	private Booking booking;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "psp_id", referencedColumnName = "psp_id")
	private Psp psp;
	
}
