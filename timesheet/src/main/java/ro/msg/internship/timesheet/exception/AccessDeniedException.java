package ro.msg.internship.timesheet.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("access denied");
    }
}
