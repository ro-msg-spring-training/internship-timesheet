package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.service.ProgramService;
import ro.msg.internship.timesheet.service.UserService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final ProgramService programService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users) {
            userDtos.add(UserBuilder.getDtoFromUser(u));
        }
        return ResponseEntity.accepted().body(userDtos);
    }

    @PostMapping(value = "/createUser", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<UserDto> createUser(@ModelAttribute UserDto userDto){
        Program program = programService.getProgramByName(userDto.getProgramName());
        User user = UserBuilder.getUserFromDto(userDto);
        user.setProgram(program);
        User createdUser = userService.createUser(user);
        return ResponseEntity.accepted().body(UserBuilder.getDtoFromUser(createdUser));
    }
}
