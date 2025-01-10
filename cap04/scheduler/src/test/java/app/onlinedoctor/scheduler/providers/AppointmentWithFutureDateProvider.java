package app.onlinedoctor.scheduler.providers;

import app.onlinedoctor.scheduler.outgoing.messaging.appointment.AppointmentDTO;
import org.instancio.Instancio;
import org.instancio.junit.GivenProvider;

import static org.instancio.Select.field;

public class AppointmentWithFutureDateProvider implements GivenProvider {
   @Override
   public Object provide(ElementContext context) {
      return Instancio.of(AppointmentDTO.class)
         .generate(field(AppointmentDTO::getStartTime),
            gen -> gen.temporal().offsetDateTime().future())
         .create();
   }
}
