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
import ro.msg.internship.timesheet.exception.PspNotFoundException;
import ro.msg.internship.timesheet.model.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PspServiceTest {
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

    @Before
    public void init() {
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

        bookingDetail = bookingDetailService.createBookingDetail(bookingDetail, booking.getDay(),
                user.getUserId());
    }

    @Test
    public void getPspByIdTest() {
        // Given
        Integer pspId = psp.getPspId();
        String expectedName = psp.getName();

        // When
        Psp result = pspService.getPspById(pspId);

        // Then
        assertThat(result.getName()).isEqualTo(expectedName);
    }

    @Test(expected = PspNotFoundException.class)
    public void getPspByIdTestFail() {
        pspService.getPspById(-2);
    }

    @After
    public void clear() {
        bookingDetailService.deleteAll();
        bookingService.deleteAll();
        pspService.deleteAll();
        userService.deleteAll();
        programService.deleteAll();
        System.out.println("delete all");
    }
}
