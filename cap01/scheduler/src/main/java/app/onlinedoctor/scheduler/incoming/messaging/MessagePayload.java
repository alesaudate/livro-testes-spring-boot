package app.onlinedoctor.scheduler.incoming.messaging;

import app.onlinedoctor.scheduler.incoming.messaging.patient.PatientDTO;
import app.onlinedoctor.scheduler.incoming.messaging.practitioner.PractitionerDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.DEDUCTION;

@JsonTypeInfo(use = DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(PatientDTO.class),
        @JsonSubTypes.Type(PractitionerDTO.class),
})
public abstract class MessagePayload {
}
