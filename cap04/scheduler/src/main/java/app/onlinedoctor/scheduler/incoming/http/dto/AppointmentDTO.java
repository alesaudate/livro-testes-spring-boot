package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.OffsetDateTime;

@Data
@Builder
public class AppointmentDTO {

   private final OffsetDateTime startTime;
   private final Duration duration;
   private final PatientDTO patientDTO;
   private final PractitionerDTO practitionerDTO;
}
