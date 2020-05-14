package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        User loggedUser = loginService.loginUser(UserBuilder.getUserFromDto(user));

        return ResponseEntity.ok(UserBuilder.getDtoFromUser(loggedUser));
    }

}
