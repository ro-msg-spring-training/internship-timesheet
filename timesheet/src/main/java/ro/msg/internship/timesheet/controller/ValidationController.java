package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.msg.internship.timesheet.dto.ProgramDto;
import ro.msg.internship.timesheet.service.ValidationService;

@Controller
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping(value = "/programNameValidation", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Boolean> validateProgramName(@ModelAttribute ProgramDto programDto) {

        boolean result = validationService.validateProgramName(programDto.getName());

        return ResponseEntity.accepted().body(result);
    }
}
