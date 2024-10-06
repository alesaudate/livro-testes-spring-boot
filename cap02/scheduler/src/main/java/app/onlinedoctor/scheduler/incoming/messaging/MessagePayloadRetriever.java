package app.onlinedoctor.scheduler.incoming.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagePayloadRetriever {

   private final ObjectMapper objectMapper;

   public <T> T getPayload(String rawMessage) {
      try {
         var message = objectMapper.readValue(rawMessage, Message.class);
         return (T) message.getPayload();
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }
}
