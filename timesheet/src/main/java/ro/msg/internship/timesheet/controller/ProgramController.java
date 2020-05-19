package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.ProgramDto;
import ro.msg.internship.timesheet.dto.PspDto;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.dto.builder.ProgramBuilder;
import ro.msg.internship.timesheet.dto.builder.PspBuilder;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.service.ProgramService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @GetMapping("/programs/{programName}/psps")
    public ResponseEntity<List<PspDto>> getPsps(@PathVariable String programName) {
        List<PspDto> pspDtos = new ArrayList<>();
        programService.getPspsByName(programName).forEach(psp -> pspDtos.add(PspBuilder.getDtoFromEntity(psp)));
        return ResponseEntity.accepted().body(pspDtos);
    }

    @GetMapping("/programs/{programName}/users")
    public ResponseEntity<List<UserDto>> getUsers(@PathVariable String programName) {
        List<UserDto> userDtos = new ArrayList<>();
        programService.getUsersByName(programName).forEach(user -> userDtos.add(UserBuilder.getDtoFromUser(user)));
        return ResponseEntity.accepted().body(userDtos);
    }

    @GetMapping("/programs")
    public ResponseEntity<List<ProgramDto>> getPrograms() {
        List<ProgramDto> programDtos = new ArrayList<>();
        programService.getPrograms().forEach(programEntity -> programDtos.add(ProgramBuilder.getDtoFromEntity(programEntity)));
        return ResponseEntity.accepted().body(programDtos);
    }

    @GetMapping("/programs/{programId}")
    public ResponseEntity<ProgramDto> getProgram(@PathVariable Integer programId) {
        return ResponseEntity.accepted().body(ProgramBuilder.getDtoFromEntity(programService.getProgramById(programId)));
    }

    @PostMapping("/programs")
    public ResponseEntity<ProgramDto> createProgram(@RequestBody ProgramDto programDto) {
        Program createdProgram = programService.createProgram(ProgramBuilder.getEntityFromDto(programDto));
        return ResponseEntity.accepted().body(ProgramBuilder.getDtoFromEntity(createdProgram));
    }

    @PutMapping("/programs")
    public ResponseEntity<ProgramDto> updateProgram(@RequestBody ProgramDto programDto) {
        Program updatedProgram = programService.updateProgram(ProgramBuilder.getEntityFromDto(programDto));
        return ResponseEntity.accepted().body(ProgramBuilder.getDtoFromEntity(updatedProgram));
    }

    @DeleteMapping("/programs/{programId}")
    public ResponseEntity<ProgramDto> deleteProgram(@PathVariable Integer programId) {
        return ResponseEntity.accepted().body(ProgramBuilder.getDtoFromEntity(programService.deleteProgramById(programId)));
    }

    @GetMapping("/activePrograms")
    public ResponseEntity<List<ProgramDto>> getActivePrograms() {
        List<ProgramDto> programDtos = new ArrayList<>();
        programService.getActivePrograms().forEach(programEntity -> programDtos.add(ProgramBuilder.getDtoFromEntity(programEntity)));
        return ResponseEntity.accepted().body(programDtos);
    }

}
