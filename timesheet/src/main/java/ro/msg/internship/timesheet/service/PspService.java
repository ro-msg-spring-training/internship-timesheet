package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.PspRepository;

@Service
@RequiredArgsConstructor
public class PspService {
    private final PspRepository pspRepository;

    public Psp getPsp(String name) {
        return pspRepository.findPspByName(name).get(0);
    }

}
