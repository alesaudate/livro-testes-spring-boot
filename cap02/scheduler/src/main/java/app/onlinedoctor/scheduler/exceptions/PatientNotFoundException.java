package app.onlinedoctor.scheduler.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PatientNotFoundException extends RuntimeException{

    private final UUID patientId;

    public PatientNotFoundException(UUID patientId, Throwable cause) {
        super(cause);
        this.patientId = patientId;
    }
}
