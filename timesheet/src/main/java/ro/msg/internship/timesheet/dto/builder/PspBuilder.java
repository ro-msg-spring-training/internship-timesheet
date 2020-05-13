package ro.msg.internship.timesheet.dto.builder;

import ro.msg.internship.timesheet.dto.PspDto;
import ro.msg.internship.timesheet.model.Psp;

public class PspBuilder {
    public static PspDto getDtoFromEntity(Psp psp) {
        return PspDto.builder()
                .pspName(psp.getName())
                .pspId(psp.getPspId())
                .build();
    }

    public static Psp getEntityFromDto(PspDto pspDto) {

        return Psp.builder()
                .name(pspDto.getPspName())
                .pspId(pspDto.getPspId())
                .build();
    }
}
