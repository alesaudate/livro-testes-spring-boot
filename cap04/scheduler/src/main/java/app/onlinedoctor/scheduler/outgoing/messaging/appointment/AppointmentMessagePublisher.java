package app.onlinedoctor.scheduler.outgoing.messaging.appointment;

import app.onlinedoctor.scheduler.outgoing.messaging.MessagePublisher;
import app.onlinedoctor.scheduler.outgoing.messaging.MessageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentMessagePublisher {

   private final MessagePublisher messagePublisher;
   private final MessageWrapper messageWrapper;

   public void broadcastAppointmentCreated(AppointmentDTO appointmentDTO) {
      var message = messageWrapper.wrapInMessage(appointmentDTO);
      messagePublisher.publish("data_appointment", message);
   }
}
