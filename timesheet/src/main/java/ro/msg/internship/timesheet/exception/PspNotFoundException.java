package ro.msg.internship.timesheet.exception;

public class PspNotFoundException extends RuntimeException {
    public PspNotFoundException() {
        super("Psp not found");
    }
}
