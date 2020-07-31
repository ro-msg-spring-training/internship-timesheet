package ro.msg.internship.timesheet.exception;

public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException() {
        super("Incorrect password");
    }

}
