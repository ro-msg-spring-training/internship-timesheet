package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.ProgramDto;
import ro.msg.internship.timesheet.model.Program;

public class ProgramBuilder {

    public static ProgramDto getDtoFromEntity(Program program) {
        return ProgramDto.builder()
                .name(program.getName())
                .programId(program.getProgramId())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .workingHours(program.getWorkingHours())
                .build();
    }

    public static Program getEntityFromDto(ProgramDto programDto) {
        return Program.builder()
                .name(programDto.getName())
                .programId(programDto.getProgramId())
                .startDate(programDto.getStartDate())
                .endDate(programDto.getEndDate())
                .workingHours(programDto.getWorkingHours())
                .build();
    }

}
