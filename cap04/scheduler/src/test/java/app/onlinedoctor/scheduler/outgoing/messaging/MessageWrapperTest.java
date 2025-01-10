package app.onlinedoctor.scheduler.outgoing.messaging;

import app.onlinedoctor.scheduler.outgoing.messaging.appointment.AppointmentDTO;
import app.onlinedoctor.scheduler.providers.AppointmentWithFutureDateProvider;
import org.instancio.junit.Given;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class MessageWrapperTest {

   private static final String TIMESTAMP = "2024-11-03T18:11:00Z";

   MessageWrapper messageWrapper;

   @BeforeEach
   void setup() {
      messageWrapper = new MessageWrapper(
         Clock.fixed(Instant.parse(TIMESTAMP), ZoneId.of("America/Sao_Paulo"))
      );
   }

   @ParameterizedTest
   @InstancioSource(samples = 5)
   void wrapInMessage_givenAnAppointment_thenAppointmentIsWrappedProperlyInMessage(
      @Given(AppointmentWithFutureDateProvider.class) AppointmentDTO appointmentDTO
   ) {
      var message = messageWrapper.wrapInMessage(appointmentDTO);

      assertThat(message).isNotNull();
      assertThat(message.getPayload()).isEqualTo(appointmentDTO);
      assertThat(message.getMetadata()).isNotNull();
      assertThat(message.getMetadata().getOrigin())
         .isEqualTo("scheduler");
      assertThat(message.getMetadata().getTimestamp())
         .isEqualTo(TIMESTAMP);
   }
}