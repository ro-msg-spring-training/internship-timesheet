package ro.msg.internship.timesheet.exception;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException() {
        super("Booking not found");
    }

}
