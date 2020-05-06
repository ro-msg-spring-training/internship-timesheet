package ro.msg.internship.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Column(name = "booking_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@Column(name = "day", nullable = false)
	private LocalDate day;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "booking")
	private Set<BookingDetail> bookingDetails;
	
}
