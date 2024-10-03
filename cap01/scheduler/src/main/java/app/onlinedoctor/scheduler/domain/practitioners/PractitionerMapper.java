package app.onlinedoctor.scheduler.domain.practitioners;

import app.onlinedoctor.scheduler.outgoing.database.PractitionerEntity;
import app.onlinedoctor.scheduler.outgoing.http.practitioners.PractitionerDTO;
import org.springframework.stereotype.Component;

@Component("domainPractitionerMapper")
public class PractitionerMapper {

    public Practitioner mapToDomain(PractitionerEntity practitionerEntity) {
        return Practitioner.builder()
                .id(practitionerEntity.getId())
                .name(practitionerEntity.getName())
                .build();
    }

    public PractitionerEntity mapToDatabase(Practitioner practitioner){
        return PractitionerEntity.builder()
                .id(practitioner.getId())
                .name(practitioner.getName())
                .build();
    }

    public Practitioner mapToDomain(PractitionerDTO practitionerDTO) {
        return Practitioner.builder()
                .id(practitionerDTO.getId())
                .name(practitionerDTO.getName())
                .build();
    }

    public PractitionerEntity mapToDatabase(PractitionerDTO practitionerDTO) {
        return PractitionerEntity.builder()
                .id(practitionerDTO.getId())
                .name(practitionerDTO.getName())
                .build();
    }
}
