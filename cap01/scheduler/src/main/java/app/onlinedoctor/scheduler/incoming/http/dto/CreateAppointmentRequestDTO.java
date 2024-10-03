package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class CreateAppointmentRequestDTO {

   private final OffsetDateTime startTime;
   private final Duration duration;
   private final UUID patientID;
   private final UUID practitionerID;

}
