package ro.msg.internship.timesheet.service.unit;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.repository.ProgramRepository;
import ro.msg.internship.timesheet.service.ValidationService;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ValidationService validationService;

    @Test
    public void validateProgramNameExist(){

        Program program1 = Program.builder().name("Summer School 2019")
                .startDate(LocalDate.of(2019, 7, 15))
                .endDate(LocalDate.of(2019, 9, 15))
                .workingHours(4.0)
                .build();
        when(programRepository.findByName("Summer School 2019")).thenReturn(java.util.Optional.ofNullable(program1));

        boolean result = validationService.validateProgramName("Summer School 2019");
        Assert.assertTrue(result);
    }

    @Test
    public void validateProgramNameNotFound(){
        when(programRepository.findByName("Summer School 2020")).thenReturn(java.util.Optional.empty());

        boolean result = validationService.validateProgramName("Summer School 2020");
        Assert.assertFalse(result);
    }

}
