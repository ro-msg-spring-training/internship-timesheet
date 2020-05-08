package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.PspRepository;

@Service
@RequiredArgsConstructor
public class PspService {
    private final PspRepository pspRepository;

    public Psp getPspById(Integer id){
        return pspRepository.findById(id).orElseThrow(() -> new RuntimeException("No psp with id:" + id));
    }

}
