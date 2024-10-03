package app.onlinedoctor.scheduler.incoming.http;

import app.onlinedoctor.scheduler.domain.practitioners.Practitioner;
import app.onlinedoctor.scheduler.incoming.http.dto.PractitionerDTO;
import org.springframework.stereotype.Component;

@Component("incomingHttpPractitionerMapper")
public class PractitionerMapper {

   public PractitionerDTO mapToIncoming(Practitioner practitioner) {
      return PractitionerDTO.builder()
         .id(practitioner.getId())
         .name(practitioner.getName())
         .build();
   }
}
