package app.onlinedoctor.scheduler.incoming.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageMetadata {

    private String origin;
    private OffsetDateTime timestamp;


}
