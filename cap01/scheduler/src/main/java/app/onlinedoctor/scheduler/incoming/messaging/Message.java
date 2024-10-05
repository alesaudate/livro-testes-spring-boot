package app.onlinedoctor.scheduler.incoming.messaging;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.OffsetDateTime;

@Data
@Builder
@Jacksonized
public class Message<T extends MessagePayload> {

   private Metadata metadata;
   private T payload;

   @Data
   @Builder
   @Jacksonized
   public static class Metadata {

      private String origin;
      private OffsetDateTime timestamp;
   }
}
