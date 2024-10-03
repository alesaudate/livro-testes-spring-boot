package app.onlinedoctor.scheduler.outgoing.messaging;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message<T> {

    private MessageMetadata metadata;
    private T payload;

}
