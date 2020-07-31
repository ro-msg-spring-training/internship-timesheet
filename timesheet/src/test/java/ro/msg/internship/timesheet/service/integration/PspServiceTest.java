package ro.msg.internship.timesheet.service.integration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.internship.timesheet.exception.PspNotFoundException;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.service.ProgramService;
import ro.msg.internship.timesheet.service.PspService;

import java.time.LocalDate;
import java.util.List;

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

    private Program program;
    private Psp psp;

    public void populate() {
        program = new Program();
        program.setName("Summer School 2019");
        program.setStartDate(LocalDate.of(2019, 7, 15));
        program.setEndDate(LocalDate.of(2019, 8, 3));
        program.setWorkingHours(8.00);

        program = programService.createProgram(program);

        psp = new Psp();
        psp.setName("PSP Self Study");
        psp.setProgram(program);
    }

    @Test
    public void createPspTest() {
        this.populate();
        Assert.assertEquals(0, pspService.getPsps().size());

        Psp createdPsp = pspService.createPsp(psp);

        Assert.assertEquals(1, pspService.getPsps().size());
        Assert.assertEquals(psp.getName(), createdPsp.getName());
        Assert.assertEquals(psp.getProgram().getName(), createdPsp.getProgram().getName());
    }

    @Test
    public void getPspByIdTest() {
        this.populate();
        psp = pspService.createPsp(psp);

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
        this.populate();
        pspService.getPspById(2);
    }

    @Test
    public void getPspsTest() {
        this.populate();
        Assert.assertEquals(0, pspService.getPsps().size());

        pspService.createPsp(psp);
        pspService.createPsp(new Psp(null, "Test Psp 2", program));

        List<Psp> createdPsps = pspService.getPsps();
        Assert.assertEquals(2, createdPsps.size());
        Assert.assertEquals(psp.getName(), createdPsps.get(0).getName());
        Assert.assertEquals("Test Psp 2", createdPsps.get(1).getName());
    }

    @Test
    public void deleteAllTest() {
        this.populate();
        Assert.assertEquals(0, pspService.getPsps().size());

        pspService.createPsp(psp);
        pspService.createPsp(new Psp(null, "Test Psp 2", program));
        Assert.assertEquals(2, pspService.getPsps().size());

        pspService.deleteAll();
        Assert.assertEquals(0, pspService.getPsps().size());
    }

    @Test
    public void updatePspTest() {
        this.populate();
        Psp createdPsp = pspService.createPsp(psp);
        createdPsp.setName("Updated Psp Name");

        Psp updatedPsp = pspService.updatePsp(createdPsp);
        Assert.assertEquals(createdPsp.getName(), updatedPsp.getName());
    }

    @Test
    public void deletePspById() {
        this.populate();
        Assert.assertEquals(0, pspService.getPsps().size());

        Psp createdPsp = pspService.createPsp(psp);
        pspService.createPsp(new Psp(null, "Test Psp 2", program));
        Assert.assertEquals(2, pspService.getPsps().size());

        Psp deletedPsp = pspService.deletePspById(createdPsp.getPspId());
        Assert.assertEquals(createdPsp.getPspId(), deletedPsp.getPspId());
        Assert.assertEquals(1, pspService.getPsps().size());
    }

    @Before
    @After
    public void clear() {
        pspService.deleteAll();
        programService.deleteAll();
    }
}
