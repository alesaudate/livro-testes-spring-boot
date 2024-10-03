package app.onlinedoctor.scheduler.outgoing.http.practitioners;

import app.onlinedoctor.scheduler.exceptions.PatientNotFoundException;
import app.onlinedoctor.scheduler.exceptions.PractitionerNotFoundException;
import app.onlinedoctor.scheduler.exceptions.ProxyRequestFailureException;
import app.onlinedoctor.scheduler.outgoing.http.patients.PatientDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class PractitionerAPIClient {

    @Value("${application.outgoing.practitioner-service.host}")
    private String practitionerServiceHost;
    private RestTemplate practitionerAPITemplate;

    @PostConstruct
    public void setup() {
        practitionerAPITemplate = new RestTemplateBuilder()
                .rootUri(practitionerServiceHost)
                .build();
    }


    public PractitionerDTO loadPractitioner(UUID practitionerId) {
        try {
            var response = practitionerAPITemplate.getForEntity("/api/v1/practitioners/{}", PractitionerDTO.class, practitionerId);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new PractitionerNotFoundException(practitionerId, e);
        }
        catch (Exception e) {
            throw new ProxyRequestFailureException(e, "patient service");
        }
    }

}
