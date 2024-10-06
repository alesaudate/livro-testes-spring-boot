package app.onlinedoctor.scheduler.outgoing.http.practitioners;

import app.onlinedoctor.scheduler.exceptions.PractitionerNotFoundException;
import app.onlinedoctor.scheduler.exceptions.ProxyRequestFailureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class PractitionerAPIClient {

   @Value("${application.outgoing.practitioner-service.host}")
   private String practitionerServiceHost;
   private RestTemplate practitionerAPI;

   @PostConstruct
   public void setup() {
      practitionerAPI = new RestTemplateBuilder()
         .rootUri(practitionerServiceHost)
         .build();
   }

   public PractitionerDTO loadPractitioner(UUID practitionerId) {
      try {
         return practitionerAPI.getForObject(
            "/api/v1/practitioners/{}",
            PractitionerDTO.class,
            practitionerId);
      } catch (HttpClientErrorException.NotFound e) {
         throw new PractitionerNotFoundException(practitionerId, e);
      } catch (Exception e) {
         throw new ProxyRequestFailureException(e, "patient service");
      }
   }
}
