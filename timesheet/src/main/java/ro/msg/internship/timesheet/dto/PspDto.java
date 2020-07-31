package ro.msg.internship.timesheet.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PspDto {
    private Integer pspId;
    private String pspName;
}
