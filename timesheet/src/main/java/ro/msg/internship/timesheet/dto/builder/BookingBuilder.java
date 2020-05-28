package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.BookingDetailDto;
import ro.msg.internship.timesheet.dto.BookingDto;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class BookingBuilder {

	public static BookingDto getDtoFromEntity(Booking booking) {
		return BookingDto.builder()
				.bookingId(booking.getBookingId())
				.day(booking.getDay())
				.startHour(getStartHour(booking))
				.endHour(getEndHour(booking))
				.hours(getHours(booking))
				.bookingDetails(getBookingDetails(booking))
				.build();
	}

	private static List<BookingDetailDto> getBookingDetails(Booking booking) {
		List<BookingDetailDto> bookingDetails = new ArrayList<>();

		booking.getBookingDetails().forEach(bookingDetailEntity -> bookingDetails
				.add(BookingDetailBuilder.getDtoFromEntity(bookingDetailEntity)));

		bookingDetails.sort((o1, o2) -> o1.getStartHour().compareTo(o2.getStartHour()));

		return bookingDetails;
	}
	
	private static String getStartHour(Booking booking) {
		LocalTime startHour = LocalTime.of(20, 0);
		
		for(BookingDetail bookingDetail : booking.getBookingDetails()) {
			if(bookingDetail.getStartHour().compareTo(startHour) < 0) {
				startHour = bookingDetail.getStartHour();
			}
		}

		return LocalTime.parse(startHour.toString(), DateTimeFormatter.ofPattern("HH:mm")).toString();
	}
	
	private static String getEndHour(Booking booking) {
		LocalTime endHour = LocalTime.of(8, 0);
		
		for(BookingDetail bookingDetail : booking.getBookingDetails()) {
			if(bookingDetail.getEndHour().compareTo(endHour) > 0) {
				endHour = bookingDetail.getEndHour();
			}
		}
		
		return LocalTime.parse(endHour.toString(), DateTimeFormatter.ofPattern("HH:mm")).toString();
	}
	
	private static double getHours(Booking booking) {
		double hours = 0;
		DecimalFormat decimalFormat = new DecimalFormat("0.00");

		for(BookingDetail bookingDetail : booking.getBookingDetails()) {
			hours += MINUTES.between(bookingDetail.getStartHour(), bookingDetail.getEndHour()) / 60.0;
		}

		return Double.parseDouble(decimalFormat.format(hours));
	}

}
