package app.onlinedoctor.scheduler.domain.practitioners;

import app.onlinedoctor.scheduler.outgoing.database.PractitionerEntity;
import org.springframework.stereotype.Component;

@Component("domainPractitionerMapper")
public class PractitionerMapper {

    public Practitioner mapToDomain(PractitionerEntity practitionerEntity) {
        return Practitioner.builder()
                .id(practitionerEntity.getId())
                .name(practitionerEntity.getName())
                .build();
    }

    public PractitionerEntity mapToEntity(Practitioner practitioner){
        return PractitionerEntity.builder()
                .id(practitioner.getId())
                .name(practitioner.getName())
                .build();
    }

}
