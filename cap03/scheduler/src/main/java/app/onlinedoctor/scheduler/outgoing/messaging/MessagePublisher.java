package app.onlinedoctor.scheduler.outgoing.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagePublisher {

   private final KafkaTemplate<String, String> kafkaTemplate;
   private final ObjectMapper objectMapper;

   public <T> void publish(String topic, Message<T> message) {
      try {
         var payloadAsString = objectMapper.writeValueAsString(message);
         kafkaTemplate.send(topic, payloadAsString);
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }
}
