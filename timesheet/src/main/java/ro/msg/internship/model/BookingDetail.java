package ro.msg.internship.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
	@Column(name = "booking_detail_id", nullable = false, unique = true)
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
	@MapsId("bookingId")
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@ManyToOne
	@MapsId("pspId")
	@JoinColumn(name = "psp_id")
	private Psp psp;
	
}
