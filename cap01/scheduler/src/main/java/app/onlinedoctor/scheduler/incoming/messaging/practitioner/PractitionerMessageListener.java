package app.onlinedoctor.scheduler.incoming.messaging.practitioner;

import app.onlinedoctor.scheduler.domain.practitioners.PractitionerService;
import app.onlinedoctor.scheduler.incoming.messaging.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static app.onlinedoctor.scheduler.incoming.messaging.ObjectMapperFactory.createMessagingObjectMapper;

@Service
@RequiredArgsConstructor
public class PractitionerMessageListener {

    private final PractitionerService practitionerService;
    private final PractitionerMapper practitionerMapper;
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setup() {
        this.objectMapper = createMessagingObjectMapper();
    }

    @KafkaListener(topics = "${application.messaging.topics.practitionerTopic:data_practitioner}")
    public void listenToPractitionerMessage(String messageAsString) throws JsonProcessingException {
        var message = objectMapper.readValue(messageAsString, Message.class);
        var payloadPractitioner = (PractitionerDTO)message.getPayload();
        var practitioner = practitionerMapper.mapToDomain(payloadPractitioner);
        practitionerService.savePractitioner(practitioner);
    }
}
