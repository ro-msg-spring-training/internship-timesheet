package ro.msg.internship.timesheet.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.internship.timesheet.exception.BookingDetailNotFoundException;
import ro.msg.internship.timesheet.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookingDetailServiceTest {
    @Autowired
    private PspService pspService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingDetailService bookingDetailService;

    @Before
    private void deleteElems() {
        bookingDetailService.deleteAll();
        bookingService.deleteAll();
        userService.deleteAll();
        programService.deleteAll();
        pspService.deleteAll();
    }

    @Before
    private void createElems(Program program, Psp psp, User user, Booking booking, BookingDetail bookingDetail) {

        program.setEndDate(LocalDate.of(2019, 8, 3));
        program.setStartDate(LocalDate.of(2019, 7, 15));
        program.setName("Summer School 2019");
        program.setWorkingHours(8.00);
        program.setProgramId(1);

        program = programService.createProgram(program);

        user.setFirstName("Patricia");
        user.setLastName("Truta");
        user.setUsername("tpatricia");
        user.setPassword("tpatricia");
        user.setRole(Role.USER);
        user.setProgram(program);
        user.setUserId(1);

        user = userService.createUser(user);
        System.out.println(user.getUserId());

        psp.setName("PSP Self Study");
        psp.setProgram(program);
        psp.setPspId(1);

        psp = pspService.createPsp(psp);

        booking.setDay(LocalDate.of(2020, 5, 30));
        booking.setUser(user);
        booking.setBookingId(1);

        booking = bookingService.getOrCreateBooking(booking);

        bookingDetail.setPsp(psp);
        bookingDetail.setBooking(booking);
        bookingDetail.setStartHour(LocalTime.of(8, 0));
        bookingDetail.setEndHour(LocalTime.of(16, 0));
        bookingDetail.setDescription("Value");
        bookingDetail.setPsp(psp);
        bookingDetail.setStatus(Status.CREATED);
        bookingDetail.setBooking(booking);
        bookingDetail.setBookingDetailId(1);

        bookingDetailService.createBookingDetail(bookingDetail, booking.getDay(),
                userService.findUserById(userService.getUsers().get(0).getUserId()).getUserId());
    }

    @Test
    public void createBookingDetailTest() {
        Program program = new Program();
        Psp psp = new Psp();
        User user = new User();
        Booking booking = new Booking();
        BookingDetail bookingDetail = new BookingDetail();
        createElems(program, psp, user, booking, bookingDetail);
        assertEquals(bookingDetailService.createBookingDetail(bookingDetail, booking.getDay(),
                userService.findUserById(userService.getUsers().get(0).getUserId()).getUserId())
                .getEndHour(), bookingDetail.getEndHour());
    }

    @Test
    public void deleteBookingDetailTest() {
        Program program = new Program();
        Psp psp = new Psp();
        User user = new User();
        Booking booking = new Booking();
        BookingDetail bookingDetail = new BookingDetail();
        createElems(program, psp, user, booking, bookingDetail);
        assertEquals(bookingDetailService
                .deleteBookingDetail(bookingDetailService
                        .getBookingDetailsById(bookingDetailService
                                .getBookingDetails().get(0).getBookingDetailId()).getBookingDetailId())
                .getEndHour(), bookingDetail.getEndHour());
    }

    @Test(expected = BookingDetailNotFoundException.class)
    public void deleteBookingDetailFailTest() {
        Program program = new Program();
        Psp psp = new Psp();
        User user = new User();
        Booking booking = new Booking();
        BookingDetail bookingDetail = new BookingDetail();
        createElems(program, psp, user, booking, bookingDetail);
        bookingDetailService.deleteBookingDetail(-1);
    }

}