package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.User;

public class UserBuilder {

    public static UserDto getDtoFromUser(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .programName(user.getProgram().getName())
                .id(user.getUserId())
                .password(user.getPassword())
                .username(user.getUsername()).build();
    }

    public static User getUserFromDto(UserDto userDto) {
        return User.builder()
                .userId(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .program(Program.builder().name(userDto.getProgramName()).build())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .bookings(null).role(null).build();
    }
}
