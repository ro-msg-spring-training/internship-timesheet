package ro.msg.internship.timesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProgramDto {

    private String name;
    private Integer programId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double workingHours;

}
