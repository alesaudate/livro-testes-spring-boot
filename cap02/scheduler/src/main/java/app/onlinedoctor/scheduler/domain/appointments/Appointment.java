package app.onlinedoctor.scheduler.domain.appointments;

import app.onlinedoctor.scheduler.domain.patients.Patient;
import app.onlinedoctor.scheduler.domain.practitioners.Practitioner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    private OffsetDateTime startTime;
    private Duration duration;
    private Patient patient;
    private Practitioner practitioner;

}
