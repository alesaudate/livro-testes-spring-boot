package app.onlinedoctor.scheduler.incoming.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message<T extends MessagePayload> {

    private MessageMetadata metadata;
    private T payload;

}
