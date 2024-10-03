package app.onlinedoctor.scheduler.domain.patients;

import app.onlinedoctor.scheduler.outgoing.database.PatientEntity;
import app.onlinedoctor.scheduler.outgoing.http.patients.PatientDTO;
import org.springframework.stereotype.Component;

@Component("domainPatientMapper")
public class PatientMapper {

    public Patient mapToDomain(PatientEntity patientEntity) {
        return Patient.builder()
                .id(patientEntity.getId())
                .name(patientEntity.getName())
                .dateOfBirth(patientEntity.getDateOfBirth())
                .build();
    }

    public Patient mapToDomain(PatientDTO patientDTO) {
        return Patient.builder()
                .id(patientDTO.getPatientID())
                .name(patientDTO.getName())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .build();
    }

    public PatientEntity mapToDatabaseEntity(PatientDTO patientDTO) {
        return PatientEntity.builder()
                .name(patientDTO.getName())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .id(patientDTO.getPatientID())
                .build();
    }

    public PatientEntity mapToDatabaseEntity(Patient patient) {
        return PatientEntity.builder()
                .id(patient.getId())
                .name(patient.getName())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }

}
