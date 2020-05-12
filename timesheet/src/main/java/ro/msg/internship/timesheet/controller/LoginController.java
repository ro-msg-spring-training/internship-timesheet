package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.service.LoginService;

@CrossOrigin("*")

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/loginUser")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) {

        User loggedUser = loginService.loginUser(UserBuilder.getUserFromDto(user));

        return ResponseEntity.accepted().body(UserBuilder.getDtoFromUser(loggedUser));

    }

}
