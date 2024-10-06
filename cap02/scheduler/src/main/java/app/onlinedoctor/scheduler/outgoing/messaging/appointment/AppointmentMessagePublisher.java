package app.onlinedoctor.scheduler.outgoing.messaging.appointment;

import app.onlinedoctor.scheduler.outgoing.messaging.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentMessagePublisher {

   private final KafkaTemplate<String, String> kafkaTemplate;
   private final ObjectMapper objectMapper;
   private final Clock clock;

   public void broadcastAppointmentCreated(AppointmentDTO appointmentDTO) {
      try {
         var message = wrapInMessage(appointmentDTO);
         var payloadAsString = objectMapper.writeValueAsString(message);
         kafkaTemplate.send("data_appointment", payloadAsString);
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }

   private Message<AppointmentDTO> wrapInMessage(
      AppointmentDTO appointmentDTO
   ) {
      Message.Metadata metadata = Message.Metadata.builder()
         .origin("scheduler")
         .timestamp(OffsetDateTime.now(clock))
         .build();

      return Message.<AppointmentDTO>builder()
         .metadata(metadata)
         .payload(appointmentDTO)
         .build();
   }

}
