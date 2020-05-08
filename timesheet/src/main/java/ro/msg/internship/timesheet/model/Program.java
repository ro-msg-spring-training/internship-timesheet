package ro.msg.internship.timesheet.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Program")
@Table(name = "programs", schema = "timesheet")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {

	@Id
	@Column(name = "program_id", nullable = false, unique = true, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer programId;

	@Column(name = "program_name", nullable = false)
	private String name;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@Column(name = "working_hours", nullable = false)
	private Double workingHours;
	
	@OneToMany(mappedBy = "program",cascade = CascadeType.ALL)
	private Set<User> users;
	
	@OneToMany(mappedBy = "program",cascade = CascadeType.ALL)
	private Set<Psp> psps;

}
