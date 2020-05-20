package ro.msg.internship.timesheet.service;

import org.junit.After;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private Program program = new Program();
    private Psp psp = new Psp();
    private User user = new User();
    private Booking booking = new Booking();
    private BookingDetail bookingDetail = new BookingDetail();

    public void populate() {
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
    }

    @Test
    public void createBookingDetailTest() {
        this.populate();
        BookingDetail detail = bookingDetailService.createBookingDetail(bookingDetail, booking.getDay(), user.getUserId());
        assertEquals(detail.getEndHour(), bookingDetail.getEndHour());
    }

    @Test
    public void deleteBookingDetailTest() {
        this.populate();
        bookingDetail = bookingDetailService.createBookingDetail(bookingDetail, booking.getDay(), user.getUserId());
        assertEquals(bookingDetailService
                .deleteBookingDetail(bookingDetailService
                        .getBookingDetailsById(bookingDetailService
                                .getBookingDetails().get(0).getBookingDetailId()).getBookingDetailId())
                .getEndHour(), bookingDetail.getEndHour());
    }

    @Test(expected = BookingDetailNotFoundException.class)
    public void deleteBookingDetailFailTest() {
        this.populate();
        bookingDetailService.deleteBookingDetail(-1);
    }

    @Test
    public void updateBookingDetailTest() {
        this.populate();
        bookingDetail = bookingDetailService.createBookingDetail(bookingDetail, booking.getDay(), user.getUserId());
        bookingDetail.setDescription("test");
        BookingDetail b = bookingDetailService.updateBookingDetail(bookingDetail);
        assertNotNull(b);
        assertEquals("test", b.getDescription());
    }

    @Before
    @After
    public void clear() {
        bookingDetailService.deleteAll();
        bookingService.deleteAll();
        pspService.deleteAll();
        userService.deleteAll();
        programService.deleteAll();
    }

}