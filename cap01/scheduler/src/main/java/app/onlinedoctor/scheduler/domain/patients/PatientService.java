package app.onlinedoctor.scheduler.domain.patients;

import app.onlinedoctor.scheduler.exceptions.PatientNotFoundException;
import app.onlinedoctor.scheduler.outgoing.database.PatientRepository;
import app.onlinedoctor.scheduler.outgoing.http.PatientAPIClient;
import app.onlinedoctor.scheduler.outgoing.http.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientAPIClient patientAPIClient;


    public Patient findPatientById(UUID patientId) {
        var patient = patientRepository.findById(patientId).map(patientMapper::mapToDomain);
        return patient.or(() -> Optional.of(loadPatientFromAPI(patientId)))
                .orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    public void savePatient(Patient patient) {
        var patientEntity = patientMapper.mapToDatabaseEntity(patient);
        patientRepository.save(patientEntity);
    }

    private Patient loadPatientFromAPI(UUID patientId) {
        var loadedPatient = patientAPIClient.loadPatient(patientId);
        savePatient(loadedPatient);
        return patientMapper.mapToDomain(loadedPatient);
    }

    private void savePatient(PatientDTO patientDTO) {
        var patientEntity = patientMapper.mapToDatabaseEntity(patientDTO);
        patientRepository.save(patientEntity);
    }
}
