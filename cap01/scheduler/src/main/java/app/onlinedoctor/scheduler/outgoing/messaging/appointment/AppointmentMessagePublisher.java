package app.onlinedoctor.scheduler.outgoing.messaging.appointment;

import app.onlinedoctor.scheduler.outgoing.messaging.Message;
import app.onlinedoctor.scheduler.outgoing.messaging.MessageMetadata;
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

    @Value("${application.messaging.topics.appointmentTopic:data_appointment}")
    private String appointmentTopic;

    public void broadcastAppointmentCreated(AppointmentDTO appointmentDTO) {
        try {
            var message = wrapInMessage(appointmentDTO);
            var payloadAsString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(appointmentTopic, payloadAsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message<AppointmentDTO> wrapInMessage(AppointmentDTO appointmentDTO) {
        MessageMetadata metadata = MessageMetadata.builder()
                .origin("appointmentService")
                .timestamp(OffsetDateTime.now(clock))
                .build();

        return Message.<AppointmentDTO>builder()
                .metadata(metadata)
                .payload(appointmentDTO)
                .build();
    }


}
