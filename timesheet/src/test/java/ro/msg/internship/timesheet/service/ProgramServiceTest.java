package ro.msg.internship.timesheet.service;

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
import ro.msg.internship.timesheet.exception.ProgramNotFoundException;
import ro.msg.internship.timesheet.model.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProgramServiceTest {
    @Autowired
    private PspService pspService;
    @Autowired
    private ProgramService programService;
    private Program program;

    //@Before
    public void populate() {
        program = new Program();
        program.setName("Summer School 2019");
        program.setStartDate(LocalDate.of(2019, 7, 15));
        program.setEndDate(LocalDate.of(2019, 8, 3));
        program.setWorkingHours(8.00);
    }

    @Test
    public void createProgramTest() {
        this.populate();
        Assert.assertEquals(0, programService.getPrograms().size());

        Program createdProgram = programService.createProgram(program);

        Assert.assertEquals(1, programService.getPrograms().size());
        Assert.assertEquals(program.getName(), createdProgram.getName());
        Assert.assertEquals(program.getStartDate(), createdProgram.getStartDate());
        Assert.assertEquals(program.getEndDate(), createdProgram.getEndDate());
        Assert.assertEquals(program.getWorkingHours(), createdProgram.getWorkingHours());
    }

    @Test
    public void getProgramByIdTest() {
        this.populate();
        Program createdProgram = programService.createProgram(program);

        Assert.assertEquals(programService.getProgramById(programService
                .getPrograms().get(0).getProgramId()).getName(), createdProgram.getName());
    }

    @Test(expected = ProgramNotFoundException.class)
    public void getProgramByIdTestFail() {
        this.populate();
        programService.getProgramById(-1);
    }

    @Test
    public void getProgramsTest() {
        this.populate();
        Assert.assertEquals(0, programService.getPrograms().size());

        programService.createProgram(program);
        programService.createProgram(new Program(null, "Test Program 2",
                LocalDate.of(2020, 3, 10),
                LocalDate.of(2020, 5, 29), 6.0,
                new HashSet<>(), new HashSet<>()));

        List<Program> createdPrograms = programService.getPrograms();
        Assert.assertEquals(2, createdPrograms.size());
        Assert.assertEquals(program.getName(), createdPrograms.get(0).getName());
        Assert.assertEquals("Test Program 2", createdPrograms.get(1).getName());
    }

    @Test
    public void deleteAllTest() {
        this.populate();
        Assert.assertEquals(0, programService.getPrograms().size());

        programService.createProgram(program);
        programService.createProgram(new Program(2, "Test Program 2",
                LocalDate.of(2020, 3, 10),
                LocalDate.of(2020, 5, 29), 6.0,
                new HashSet<>(), new HashSet<>()));
        Assert.assertEquals(2, programService.getPrograms().size());

        programService.deleteAll();
        Assert.assertEquals(0, programService.getPrograms().size());
    }

    @Test
    public void getProgramByNameTest() {
        this.populate();
        programService.createProgram(program);

        Assert.assertEquals(program.getName(), programService.getProgramByName(program.getName()).getName());
    }

    @Test
    public void getPspsByNameTest() {
        this.populate();
        Program createdProgram = programService.createProgram(program);
        pspService.createPsp(new Psp(null, "Test Psp 1", createdProgram));
        pspService.createPsp(new Psp(null, "Test Psp 2", createdProgram));
        pspService.createPsp(new Psp(null, "Test Psp 3", createdProgram));

        Set<Psp> psps = programService.getPspsByName(createdProgram.getName());
        Assert.assertEquals(3, psps.size());
    }

    @Test
    public void updateProgramTest() {
        this.populate();
        Program createdProgram = programService.createProgram(program);
        createdProgram.setName("Updated Program Name");
        createdProgram.setWorkingHours(4.0);

        Program updatedProgram = programService.updateProgram(createdProgram);
        Assert.assertEquals(createdProgram.getName(), updatedProgram.getName());
        Assert.assertEquals(createdProgram.getWorkingHours(), updatedProgram.getWorkingHours());
    }

    @Test
    public void deleteProgramByIdTest() {
        this.populate();
        Assert.assertEquals(0, programService.getPrograms().size());

        Program createdProgram = programService.createProgram(program);
        programService.createProgram(new Program(2, "Test Program 2",
                LocalDate.of(2020, 3, 10),
                LocalDate.of(2020, 5, 29), 6.0,
                new HashSet<>(), new HashSet<>()));
        Assert.assertEquals(2, programService.getPrograms().size());

        Program deletedProgram = programService.deleteProgramById(createdProgram.getProgramId());
        Assert.assertEquals(1, programService.getPrograms().size());
        Assert.assertEquals(createdProgram.getProgramId(), deletedProgram.getProgramId());
    }

    @Before
    @After
    public void clear() {
        pspService.deleteAll();
        programService.deleteAll();
    }
}
