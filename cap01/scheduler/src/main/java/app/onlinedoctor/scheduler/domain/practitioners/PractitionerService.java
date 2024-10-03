package app.onlinedoctor.scheduler.domain.practitioners;

import app.onlinedoctor.scheduler.exceptions.PractitionerNotFoundException;
import app.onlinedoctor.scheduler.outgoing.database.PractitionerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PractitionerService {

    private final PractitionerRepository practitionerRepository;
    private final PractitionerMapper practitionerMapper;

    public Practitioner findPractitionerById(UUID practitionerId) {
        var practitionerEntity = practitionerRepository.findById(practitionerId);
        return practitionerEntity.map(practitionerMapper::mapToDomain)
                .orElseThrow(() -> new PractitionerNotFoundException(practitionerId));
    }

    public void savePractitioner(Practitioner practitioner) {
        var practitionerEntity = practitionerMapper.mapToEntity(practitioner);
        practitionerRepository.save(practitionerEntity);
    }

}
