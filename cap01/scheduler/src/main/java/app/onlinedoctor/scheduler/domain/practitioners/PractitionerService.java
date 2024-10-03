package app.onlinedoctor.scheduler.domain.practitioners;

import app.onlinedoctor.scheduler.exceptions.PractitionerNotFoundException;
import app.onlinedoctor.scheduler.outgoing.database.PractitionerRepository;
import app.onlinedoctor.scheduler.outgoing.http.practitioners.PractitionerAPIClient;
import app.onlinedoctor.scheduler.outgoing.http.practitioners.PractitionerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PractitionerService {

    private final PractitionerRepository practitionerRepository;
    private final PractitionerMapper practitionerMapper;
    private final PractitionerAPIClient practitionerAPIClient;

    public Practitioner findPractitionerById(UUID practitionerId) {
        var practitioner = practitionerRepository.findById(practitionerId)
                .map(practitionerMapper::mapToDomain);
        return practitioner.orElseGet(() -> loadPractitionerFromAPI(practitionerId));
    }

    public void savePractitioner(Practitioner practitioner) {
        var practitionerEntity = practitionerMapper.mapToEntity(practitioner);
        practitionerRepository.save(practitionerEntity);
    }

    private Practitioner loadPractitionerFromAPI(UUID practitionerId) {
        var loadedPractitioner = practitionerAPIClient.loadPractitioner(practitionerId);
        savePractitioner(loadedPractitioner);
        return practitionerMapper.mapToDomain(loadedPractitioner);
    }

    private void savePractitioner(PractitionerDTO practitionerDTO) {
        var practitionerEntity = practitionerMapper.mapToDatabaseEntity(practitionerDTO);
        practitionerRepository.save(practitionerEntity);
    }



}
