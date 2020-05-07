package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for(User u : users) {
            userDtos.add(UserBuilder.getDtoFromUser(u));
        }
        return userDtos;
    }
}