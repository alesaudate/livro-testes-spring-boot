package app.onlinedoctor.scheduler.incoming.http;

import app.onlinedoctor.scheduler.domain.patients.Patient;
import app.onlinedoctor.scheduler.incoming.http.dto.PatientDTO;
import org.springframework.stereotype.Component;

@Component("incomingHttpPatientMapper")
public class PatientMapper {

   public PatientDTO mapToIncoming(Patient patient) {
      return PatientDTO.builder()
         .name(patient.getName())
         .dateOfBirth(patient.getDateOfBirth())
         .id(patient.getId())
         .build();
   }
}
