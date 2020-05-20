package ro.msg.internship.timesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String programName;
    private String role;

}
