package ro.msg.internship.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Column(name = "program_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer programId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@Column(name = "working_hours", nullable = false)
	private Float workingHours;
	
	@OneToMany(mappedBy = "program")
	private Set<User> users;
	
	@OneToMany(mappedBy = "program")
	private Set<Psp> psps;

}
