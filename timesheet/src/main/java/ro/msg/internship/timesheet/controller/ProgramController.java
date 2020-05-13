package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.internship.timesheet.dto.PspDto;
import ro.msg.internship.timesheet.dto.builder.PspBuilder;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.service.ProgramService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")

@RestController
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @GetMapping("/programs/{programName}/psps")
    public ResponseEntity<List<PspDto>> getPsps(@PathVariable String programName) {

        Set<Psp> psps = programService.getPsps(programName);

        List<PspDto> pspDtos = new ArrayList<>();

        for (Psp psp : psps) {
            PspDto pspDto = PspBuilder.getDtoFromEntity(psp);
            pspDtos.add(pspDto);
        }

        return ResponseEntity.accepted().body(pspDtos);
    }
}
