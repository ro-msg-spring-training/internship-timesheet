package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.ProgramNotFoundException;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.repository.ProgramRepository;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {
	private final ProgramRepository programRepository;

	public Program getProgramById(Integer id) {
		return programRepository.findById(id).orElseThrow(ProgramNotFoundException::new);
	}

	public Program createProgram(Program program) {
		return programRepository.save(program);
	}

	public List<Program> getPrograms() {
		return programRepository.findAll();
	}

	@Transactional
	public void deleteAll() {
		programRepository.deleteAll();
	}

	public Program getProgramByName(String name) {
		return programRepository.findByName(name).orElseThrow(ProgramNotFoundException::new);
	}

	public List<String> getPsps(String name) {
		Program program = getProgramByName(name);
		List<String> pspNames = new ArrayList<>();

		program.getPsps().forEach(psp -> pspNames.add(psp.getName()));

		return pspNames;
	}
}
