package app.onlinedoctor.scheduler.incoming.messaging.patient;

import app.onlinedoctor.scheduler.domain.patients.Patient;
import org.springframework.stereotype.Component;

@Component("incomingMessagingPatientMapper")
public class PatientMapper {

    public Patient mapToDomain(PatientDTO patientDTO) {
        return Patient.builder()
                .id(patientDTO.getId())
                .name(patientDTO.getName())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .build();
    }

}
