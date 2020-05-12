package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.internship.timesheet.service.ProgramService;

import java.util.List;

@CrossOrigin("*")

@RestController
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @GetMapping("/programs/{programName}/psps")
    public ResponseEntity<List<String>> getPsps(@PathVariable String programName) {
        return ResponseEntity.accepted().body(programService.getPsps(programName));
    }
}
