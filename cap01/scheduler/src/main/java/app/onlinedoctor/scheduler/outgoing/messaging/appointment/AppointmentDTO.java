package app.onlinedoctor.scheduler.outgoing.messaging.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AppointmentDTO {

    private UUID appointmentId;
    private UUID patientID;
    private UUID practitionerID;
    private OffsetDateTime startTime;
    private Duration duration;

}
