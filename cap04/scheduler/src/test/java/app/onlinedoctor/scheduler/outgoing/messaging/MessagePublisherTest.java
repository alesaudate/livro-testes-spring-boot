package app.onlinedoctor.scheduler.outgoing.messaging;

import app.onlinedoctor.scheduler.outgoing.messaging.appointment.AppointmentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;
import java.time.OffsetDateTime;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessagePublisherTest {

   private static final String FIXED_TIMESTAMP = "2024-10-01T13:00:00.000Z";
   
   MessagePublisher messagePublisher;

   @Mock
   KafkaTemplate<String, String> kafkaTemplate;

   @Captor
   ArgumentCaptor<String> payloadCaptor;

   BasicJsonTester json;

   @BeforeEach
   void setup() {
      json = new BasicJsonTester(getClass());
      messagePublisher = new MessagePublisher(
         kafkaTemplate,
         new ObjectMapper().findAndRegisterModules());
   }

   @Test
   void publish_givenAMessage_checkPublishedDataMatchesParameter() {
      // Given
      var startTime = "2024-10-20T15:25:00.000Z";
      var appointment = AppointmentDTO.builder()
            .appointmentId(randomUUID())
            .duration(Duration.ofMinutes(30L))
            .patientID(randomUUID())
            .practitionerID(randomUUID())
            .startTime(OffsetDateTime.parse(startTime))
            .build();
      var message = Message.builder()
         .payload(appointment)
            .metadata(Message.Metadata.builder()
               .timestamp(OffsetDateTime.parse(FIXED_TIMESTAMP))
               .origin("scheduler")
               .build())
         .build();

      // When
      messagePublisher.publish("data_appointment", message);

      // Then
      verify(kafkaTemplate).send(
         eq("data_appointment"),
         payloadCaptor.capture());

      verifyPayloadMatchesExpectedAppointment(
         payloadCaptor.getValue(),
         appointment,
         startTime);
   }

   private void verifyPayloadMatchesExpectedAppointment(
      String payload,
      AppointmentDTO appointment,
      String startTime
   ) {
      assertThat(json.from(payload))
         .hasJsonPathValue("$.payload.duration", "PT30M")
         .hasJsonPathValue(
            "$.payload.appointment_id",
            appointment.getAppointmentId())
         .hasJsonPathValue(
            "$.payload.patient_id",
            appointment.getPatientID()
         )
         .hasJsonPathValue(
            "$.payload.practitioner_id",
            appointment.getPractitionerID()
         )
         .hasJsonPathValue(
            "$.payload.start_time",
            startTime
         )
         .hasJsonPathValue("$.metadata.origin", "scheduler")
         .hasJsonPathValue("$.metadata.timestamp", FIXED_TIMESTAMP);
   }
}