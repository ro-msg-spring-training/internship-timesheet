package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.service.LoginService;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) {

        System.out.println("controller: " + user);

        User loggedUser = loginService.loginUser(UserBuilder.getUserFromDto(user));

        System.out.println("logged: " + user);

        return ResponseEntity.ok(UserBuilder.getDtoFromUser(loggedUser));
    }

    @PostMapping(value = "/adminLogin", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<UserDto> loginAdmin(@ModelAttribute UserDto user) {
        User loggedUser = loginService.loginAdmin(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(UserBuilder.getDtoFromUser(loggedUser));
    }
}
