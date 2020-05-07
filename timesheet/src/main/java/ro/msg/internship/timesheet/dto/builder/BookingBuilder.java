package ro.msg.internship.timesheet.dto.builder;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ro.msg.internship.timesheet.dto.BookingDetailDto;
import ro.msg.internship.timesheet.dto.BookingDto;
import ro.msg.internship.timesheet.model.Booking;
import ro.msg.internship.timesheet.model.BookingDetail;

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
				.add(BookingDetailBuilder.getDtoFromEntity(bookingDetailEntity, 0)));
		
		return bookingDetails;
	}
	
	private static LocalTime getStartHour(Booking booking) {
		LocalTime startHour = LocalTime.of(20, 0);
		
		for(BookingDetail bookingDetail : booking.getBookingDetails()) {
			if(bookingDetail.getStartHour().compareTo(startHour) < 0) {
				startHour = bookingDetail.getStartHour();
			}
		}
		
		return startHour;
	}
	
	private static LocalTime getEndHour(Booking booking) {
		LocalTime endHour = LocalTime.of(8, 0);
		
		for(BookingDetail bookingDetail : booking.getBookingDetails()) {
			if(bookingDetail.getStartHour().compareTo(endHour) > 0) {
				endHour = bookingDetail.getStartHour();
			}
		}
		
		return endHour;
	}
	
	private static double getHours(Booking booking) {
		double hours = 0;
		
		for(BookingDetail bookingDetail : booking.getBookingDetails()) {
			hours += MINUTES.between(bookingDetail.getEndHour(), bookingDetail.getStartHour()) / 60.0;
		}
		
		return hours;
	}

}
