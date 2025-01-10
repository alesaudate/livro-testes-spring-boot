package app.onlinedoctor.scheduler.domain.patients;

import app.onlinedoctor.scheduler.outgoing.database.PatientRepository;
import app.onlinedoctor.scheduler.outgoing.http.patients.PatientAPIClient;
import app.onlinedoctor.scheduler.outgoing.http.patients.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientAPIClient patientAPIClient;


    public Patient findPatientById(UUID patientId) {
        var patient = patientRepository.findById(patientId).map(patientMapper::mapToDomain);
        return patient.orElseGet(() -> loadPatientFromAPI(patientId));
    }

    public void savePatient(Patient patient) {
        var patientEntity = patientMapper.mapToDatabase(patient);
        patientRepository.save(patientEntity);
    }

    private Patient loadPatientFromAPI(UUID patientId) {
        var loadedPatient = patientAPIClient.loadPatient(patientId);
        savePatient(loadedPatient);
        return patientMapper.mapToDomain(loadedPatient);
    }

    private void savePatient(PatientDTO patientDTO) {
        var patientEntity = patientMapper.mapToDatabase(patientDTO);
        patientRepository.save(patientEntity);
    }
}
