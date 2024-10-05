package app.onlinedoctor.scheduler.incoming.messaging.practitioner;

import app.onlinedoctor.scheduler.incoming.messaging.MessagePayload;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@Jacksonized
public class PractitionerDTO extends MessagePayload {

   private UUID id;
   private String name;
}
