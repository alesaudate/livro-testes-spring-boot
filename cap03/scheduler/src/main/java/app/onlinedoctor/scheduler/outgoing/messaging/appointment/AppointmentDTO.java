package app.onlinedoctor.scheduler.outgoing.messaging.appointment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppointmentDTO {

   private UUID appointmentId;
   private UUID patientID;
   private UUID practitionerID;
   private OffsetDateTime startTime;
   private Duration duration;

}
