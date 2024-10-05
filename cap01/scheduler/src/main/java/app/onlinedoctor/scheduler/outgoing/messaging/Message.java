package app.onlinedoctor.scheduler.outgoing.messaging;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Message<T> {

   private Metadata metadata;
   private T payload;

   @Data
   @Builder
   public static class Metadata {

      private String origin;
      private OffsetDateTime timestamp;
   }
}
