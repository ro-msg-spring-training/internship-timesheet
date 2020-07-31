package ro.msg.internship.timesheet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.internship.timesheet.dto.PspDto;
import ro.msg.internship.timesheet.dto.builder.PspBuilder;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.service.PspService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class PspController {

    private final PspService pspService;

    @GetMapping("/psps")
    public ResponseEntity<List<PspDto>> getPsps() {
        List<PspDto> pspDtos = new ArrayList<>();
        pspService.getPsps().forEach(psp -> pspDtos.add(PspBuilder.getDtoFromEntity(psp)));
        return ResponseEntity.accepted().body(pspDtos);
    }

    @GetMapping("/psps/{pspId}")
    public ResponseEntity<PspDto> getPsp(@PathVariable Integer pspId) {
        return ResponseEntity.accepted().body(PspBuilder.getDtoFromEntity(pspService.getPspById(pspId)));
    }

    @PostMapping("/psps")
    public ResponseEntity<PspDto> createPsp(@RequestBody PspDto pspDto) {
        Psp createdProgram = pspService.createPsp(PspBuilder.getEntityFromDto(pspDto));
        return ResponseEntity.accepted().body(PspBuilder.getDtoFromEntity(createdProgram));
    }

    @PutMapping("/psps")
    public ResponseEntity<PspDto> updateProgram(@RequestBody PspDto pspDto) {
        Psp updatedPsp = pspService.updatePsp(PspBuilder.getEntityFromDto(pspDto));
        return ResponseEntity.accepted().body(PspBuilder.getDtoFromEntity(updatedPsp));
    }

    @DeleteMapping("/psps/{pspId}")
    public ResponseEntity<PspDto> deletePsp(@PathVariable Integer pspId) {
        return ResponseEntity.accepted().body(PspBuilder.getDtoFromEntity(pspService.deletePspById(pspId)));
    }

}
