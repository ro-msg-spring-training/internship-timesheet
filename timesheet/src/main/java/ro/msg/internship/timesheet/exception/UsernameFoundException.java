package ro.msg.internship.timesheet.exception;

public class UsernameFoundException extends RuntimeException {
    public UsernameFoundException(String username) {
        super("There is a user with same username: " + username);
    }

}
