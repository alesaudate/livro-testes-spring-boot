package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    private OffsetDateTime startTime;
    private Duration duration;
    private final PatientDTO patientDTO;
    private final PractitionerDTO practitionerDTO;

}
