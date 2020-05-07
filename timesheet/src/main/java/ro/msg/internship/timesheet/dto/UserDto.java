package ro.msg.internship.timesheet.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    private String programeName;
}
