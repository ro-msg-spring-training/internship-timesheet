package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.Role;
import ro.msg.internship.timesheet.model.User;

public class UserBuilder {

    public static UserDto getDtoFromUser(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .programName(user.getProgram().getName())
                .id(user.getUserId())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    public static User getUserFromDto(UserDto userDto) {
        Role role = Role.USER;
        if (!(userDto.getRole() == null)) {
            role = Role.valueOf(userDto.getRole().toUpperCase());
        }
        return User.builder()
                .userId(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .program(Program.builder().name(userDto.getProgramName()).build())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .bookings(null)
                .role(role)
                .build();
    }
}
