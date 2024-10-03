package app.onlinedoctor.scheduler.incoming.http;

import app.onlinedoctor.scheduler.domain.practitioners.Practitioner;
import app.onlinedoctor.scheduler.incoming.http.dto.PractitionerDTO;
import org.springframework.stereotype.Component;

@Component("incomingHttpPractitionerMapper")
public class PractitionerMapper {


    public PractitionerDTO mapToPractitionerDTO(Practitioner practitioner) {
        return null;
    }

}
