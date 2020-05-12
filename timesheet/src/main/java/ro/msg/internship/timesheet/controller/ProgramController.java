package ro.msg.internship.timesheet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.msg.internship.timesheet.service.ProgramService;

@CrossOrigin("*")

@RestController
@RequiredArgsConstructor
public class ProgramController {

	private final ProgramService programService;
	
	@GetMapping("/programs/{programName}/psps")
	public List<String> getPsps(@PathVariable String programName) {
		return programService.getPsps(programName);
	}
	
}
