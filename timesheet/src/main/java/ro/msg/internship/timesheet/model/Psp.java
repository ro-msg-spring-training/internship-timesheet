package ro.msg.internship.timesheet.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Psp")
@Table(name = "psp", schema = "timesheet")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Psp {

	@Id
	@Column(name = "psp_id", nullable = false, unique = true,columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pspId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "program_id", referencedColumnName = "program_id")
	private Program program;
	
}
