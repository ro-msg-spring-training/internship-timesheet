package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.ProgramCreateDto;
import ro.msg.internship.timesheet.dto.ProgramDto;
import ro.msg.internship.timesheet.dto.PspDto;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.dto.builder.ProgramBuilder;
import ro.msg.internship.timesheet.dto.builder.ProgramCreateBuilder;
import ro.msg.internship.timesheet.dto.builder.PspBuilder;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.model.Role;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.service.ProgramService;
import ro.msg.internship.timesheet.service.PspService;
import ro.msg.internship.timesheet.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;
    private final PspService pspService;
    private final UserService userService;

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

    @Transactional
    @PostMapping(value = "/programs", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<ProgramDto> createProgram(@ModelAttribute ProgramCreateDto programCreateDto) {
        Program program = ProgramCreateBuilder.getEntityFromDto(programCreateDto);
        Set<Psp> psps = program.getPsps();
        Set<User> users = program.getUsers();
        Program createdProgram = programService.createProgram(program);

        for(Psp psp : psps) {
            psp.setProgram(createdProgram);
        }
        pspService.createAll(psps);
        createdProgram.setPsps(psps);

        for(User user : users){
            user.setProgram(createdProgram);
            user.setRole(Role.USER);
        }
        userService.createAll(users);
        createdProgram.setUsers(users);

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
