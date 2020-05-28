package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.msg.internship.timesheet.dto.LoginDto;
import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.dto.builder.UserBuilder;
import ro.msg.internship.timesheet.model.User;
import ro.msg.internship.timesheet.security.UserDetailService;
import ro.msg.internship.timesheet.service.LoginService;
import ro.msg.internship.timesheet.service.UserService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    /*@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) {

        System.out.println("controller: " + user);

        User loggedUser = loginService.loginUser(UserBuilder.getUserFromDto(user));

        System.out.println("logged: " + user);

        return ResponseEntity.ok(UserBuilder.getDtoFromUser(loggedUser));
    }*/

    @PostMapping(value = "/adminLogin", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<UserDto> loginAdmin(@ModelAttribute UserDto user) {
        User loggedUser = loginService.loginAdmin(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(UserBuilder.getDtoFromUser(loggedUser));
    }

    @PostMapping(value = "/loginUser", consumes = "multipart/form-data",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<List<UserDto>> loginUser(@ModelAttribute UserDto user) {
        List<User> users = loginService.loginUser(user.getUsername(), user.getPassword());
        List<UserDto> userDtos = new ArrayList<>();
        for (User u : users) {
            userDtos.add(UserBuilder.getDtoFromUser(u));
        }
        return ResponseEntity.ok(userDtos);
    }

//    @GetMapping(value = "/defaultLogin", consumes = "multipart/form-data",
//            produces = {"application/json", "application/xml"})
//    public ResponseEntity<List<UserDto>> defaultLogin(Authentication authentication) {
//        //authentication = SecurityContextHolder.getContext().getAuthentication();
//        //UserDetailService userDetailService = new UserDetailService(userService);
//        //UserDetails userDetails = userDetailService.loadUserByUsername(authentication.getName());
//        //authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) ;
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        authentication = SecurityContextHolder.getContext().getAuthentication();
////        Set<String> roles = authentication.getAuthorities().stream()
////                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
////
////        boolean hasUserRole = authentication.getAuthorities().stream()
////                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
////        System.out.println(hasUserRole);
//        //System.out.println(loginDto);
//        //List<User> users = loginService.loginUser(loginDto, loginDto, 0);
//        List<UserDto> userDtos = new ArrayList<>();
//
//        /*for (User u : users) {
//            userDtos.add(UserBuilder.getDtoFromUser(u));
//        }*/
//
//        return ResponseEntity.accepted().body(userDtos);
//    }
}
