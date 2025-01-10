package app.onlinedoctor.scheduler.incoming.messaging.patient;

import app.onlinedoctor.scheduler.incoming.messaging.MessagePayload;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@Jacksonized
public class PatientDTO extends MessagePayload {

   private UUID id;
   private String name;
   private LocalDate dateOfBirth;

}
