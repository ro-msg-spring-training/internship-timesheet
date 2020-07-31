package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.repository.ProgramRepository;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ProgramRepository programRepository;

    public boolean validateProgramName(String programName){

        if(null != programRepository.findByName(programName).orElse(null)){
            return true;
        }
        else {
            return false;
        }
    }

}
