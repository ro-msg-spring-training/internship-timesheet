package ro.msg.internship.timesheet.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
	private Set<User> users;
	
	@OneToMany(mappedBy = "program")
	private Set<Psp> psps;

}
