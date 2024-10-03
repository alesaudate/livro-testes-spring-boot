package app.onlinedoctor.scheduler.outgoing.messaging;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class MessageMetadata {

    private String origin;
    private OffsetDateTime timestamp;


}
