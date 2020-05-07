package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.UserDto;
import ro.msg.internship.timesheet.model.User;

public class UserBuilder {

    public static UserDto getDtoFromUser(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .programeName(user.getProgram().getName()).build();
    }
}
