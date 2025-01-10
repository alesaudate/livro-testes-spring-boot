package app.onlinedoctor.scheduler.incoming.messaging.practitioner;

import app.onlinedoctor.scheduler.domain.practitioners.PractitionerService;
import app.onlinedoctor.scheduler.incoming.messaging.MessagePayloadRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PractitionerMessageListener {

   private final PractitionerService practitionerService;
   private final PractitionerMapper practitionerMapper;
   private final MessagePayloadRetriever messagePayloadRetriever;

   @KafkaListener(topics = "data_practitioner")
   public void listenToPractitionerMessage(String messageAsString) {
      PractitionerDTO practitionerDTO =
         messagePayloadRetriever.getPayload(messageAsString);
      var practitioner = practitionerMapper.mapToDomain(practitionerDTO);
      practitionerService.savePractitioner(practitioner);
   }
}
