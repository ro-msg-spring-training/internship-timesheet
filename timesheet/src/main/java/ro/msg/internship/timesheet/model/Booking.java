package ro.msg.internship.timesheet.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Booking")
@Table(name = "booking", schema = "timesheet")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

	@Id
	@Column(name = "booking_id", nullable = false, unique = true, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@Column(name = "day", nullable = false)
	private LocalDate day;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private Set<BookingDetail> bookingDetails;
	
}
