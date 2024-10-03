package app.onlinedoctor.scheduler.incoming.messaging.patient;

import app.onlinedoctor.scheduler.domain.patients.PatientService;
import app.onlinedoctor.scheduler.incoming.messaging.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static app.onlinedoctor.scheduler.incoming.messaging.ObjectMapperFactory.createMessagingObjectMapper;

@Service
@RequiredArgsConstructor
public class PatientMessageListener {

    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setup() {
        this.objectMapper = createMessagingObjectMapper();
    }

    @KafkaListener(topics = "${application.messaging.topics.patientTopic:data_patient}", groupId = "scheduler-group")
    public void listenToPatientMessage(@Payload String messageAsString) throws JsonProcessingException {
        var message = objectMapper.readValue(messageAsString, Message.class);
        var payloadPractitioner = (PatientDTO)message.getPayload();
        var patient = patientMapper.mapToDomain(payloadPractitioner);
        patientService.savePatient(patient);
    }
}
