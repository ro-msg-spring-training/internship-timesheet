package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.ProgramNotFoundException;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.ProgramRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    public void deleteAll() {
        programRepository.deleteAll();
    }

    public Program getProgramByName(String name) {
        return programRepository.findByName(name).orElseThrow(ProgramNotFoundException::new);
    }

    public Set<Psp> getPspsByName(String name) {
        return getProgramByName(name).getPsps();
    }

    public Program updateProgram(Program program) {
        Optional<Program> programInDB = programRepository.findById(program.getProgramId());
        if (programInDB.isPresent()) {
            return programRepository.save(program);
        } else {
            throw new ProgramNotFoundException();
        }
    }

    public Program deleteProgramById(Integer programId) {
        Program programInDB = programRepository.findById(programId).orElseThrow(ProgramNotFoundException::new);
        programRepository.deleteById(programId);
        return programInDB;
    }
}
