package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LogoutController {
    @GetMapping(value = "/defaultLogout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("YES");
    }
}
