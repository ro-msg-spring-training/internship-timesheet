package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.PspNotFoundException;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.PspRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PspService {
    private final PspRepository pspRepository;

    @Transactional
    public Psp getPspById(Integer id){
        return pspRepository.findById(id).orElseThrow(PspNotFoundException::new);
    }

    public Psp createPsp(Psp psp){
        return pspRepository.save(psp);
    }

    public List<Psp> getPsps() {return pspRepository.findAll();}

    public void deleteAll(){
        pspRepository.deleteAll();
    }

}
