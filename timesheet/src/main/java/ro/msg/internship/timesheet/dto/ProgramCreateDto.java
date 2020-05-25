package ro.msg.internship.timesheet.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProgramCreateDto {
    private String name;
    private Integer programId;
    private String startDate;
    private String endDate;
    private Double workingHours;

    private String psps;
    private String users;
}
