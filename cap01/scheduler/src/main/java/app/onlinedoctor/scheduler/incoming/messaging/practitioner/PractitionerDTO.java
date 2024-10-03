package app.onlinedoctor.scheduler.incoming.messaging.practitioner;

import app.onlinedoctor.scheduler.incoming.messaging.MessagePayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class PractitionerDTO extends MessagePayload {

    private UUID id;
    private String name;
}
