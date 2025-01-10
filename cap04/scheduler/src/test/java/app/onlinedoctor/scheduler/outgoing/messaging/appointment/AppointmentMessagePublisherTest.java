package app.onlinedoctor.scheduler.outgoing.messaging.appointment;

import app.onlinedoctor.scheduler.outgoing.messaging.Message;
import app.onlinedoctor.scheduler.outgoing.messaging.MessagePublisher;
import app.onlinedoctor.scheduler.outgoing.messaging.MessageWrapper;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentMessagePublisherTest {

   @Mock
   MessagePublisher messagePublisher;

   @Mock
   MessageWrapper messageWrapper;

   @InjectMocks
   AppointmentMessagePublisher appointmentMessagePublisher;

   @ParameterizedTest
   @InstancioSource
   void broadcastAppointmentCreated_givenAnAppointment_thenAssertMessagePublisherIsCalledWithAMessage(AppointmentDTO appointmentDTO, Message<AppointmentDTO> message) {
      when(messageWrapper.wrapInMessage(appointmentDTO)).thenReturn(message);

      appointmentMessagePublisher.broadcastAppointmentCreated(appointmentDTO);

      verify(messagePublisher).publish("data_appointment", message);
   }
}