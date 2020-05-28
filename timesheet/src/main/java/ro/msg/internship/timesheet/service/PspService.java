package ro.msg.internship.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.internship.timesheet.exception.PspNotFoundException;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.repository.PspRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PspService {
    private final PspRepository pspRepository;

    public Psp getPspById(Integer id) {
        return pspRepository.findById(id).orElseThrow(PspNotFoundException::new);
    }

    public Psp createPsp(Psp psp) {
        return pspRepository.save(psp);
    }

    public List<Psp> createAll(Set<Psp> psps){
        return pspRepository.saveAll(psps);
    }

    public List<Psp> getPsps() {
        return pspRepository.findAll();
    }

    public void deleteAll() {
        pspRepository.deleteAll();
    }

    public Psp updatePsp(Psp psp) {
        Optional<Psp> pspInDB = pspRepository.findById(psp.getPspId());
        if (pspInDB.isPresent()) {
            return pspRepository.save(psp);
        } else {
            throw new PspNotFoundException();
        }
    }

    public Psp deletePspById(Integer pspId) {
        Psp pspInDB = pspRepository.findById(pspId).orElseThrow(PspNotFoundException::new);
        pspRepository.delete(pspInDB);
        return pspInDB;
    }
}
