package app.onlinedoctor.scheduler.domain.appointments;

import app.onlinedoctor.scheduler.domain.patients.Patient;
import app.onlinedoctor.scheduler.domain.practitioners.Practitioner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateAppointmentRequest {

    private final OffsetDateTime startTime;
    private final Duration duration;
    private final UUID patientID;
    private final UUID practitionerID;

    // Os campos abaixo serão preenchidos pela lógica da própria aplicação


    private Practitioner practitioner;
    private Patient patient;
}
