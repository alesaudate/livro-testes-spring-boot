package app.onlinedoctor.scheduler.incoming.messaging.practitioner;

import app.onlinedoctor.scheduler.domain.practitioners.Practitioner;
import org.springframework.stereotype.Component;

@Component("incomingMessagingPractitionerMapper")
public class PractitionerMapper {

   public Practitioner mapToDomain(PractitionerDTO practitionerDTO) {
      return Practitioner.builder()
         .name(practitionerDTO.getName())
         .id(practitionerDTO.getId())
         .build();
   }
}
