package ro.msg.internship.timesheet.exception;

public class ProgramNotFoundException extends RuntimeException {

    public ProgramNotFoundException() {
        super("Program not found");
    }

}
