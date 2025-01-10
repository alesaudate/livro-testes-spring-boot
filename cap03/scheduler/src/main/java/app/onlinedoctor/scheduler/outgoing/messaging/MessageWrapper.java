package app.onlinedoctor.scheduler.outgoing.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class MessageWrapper {

   private final Clock timestampClock;

   public <T> Message<T> wrapInMessage(T payload) {
      var now = OffsetDateTime.now(timestampClock);

      var metadata = Message.Metadata.builder()
         .origin("scheduler")
         .timestamp(now)
         .build();

      return Message.<T>builder()
         .metadata(metadata)
         .payload(payload)
         .build();
   }
}
