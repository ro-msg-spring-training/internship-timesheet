package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.ProgramNotFoundException;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.ProgramRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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

    public Set<Psp> getPsps(String name) {
        Program program = getProgramByName(name);

        return program.getPsps();
    }
}
