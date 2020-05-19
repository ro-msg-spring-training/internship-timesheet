package ro.msg.internship.timesheet.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity(name = "BookingDetail")
@Table(name = "booking_detail", schema = "timesheet")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
	
	@ManyToOne
	@JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
	private Booking booking;
	
	@ManyToOne
	@JoinColumn(name = "psp_id", referencedColumnName = "psp_id")
	private Psp psp;
	
}
