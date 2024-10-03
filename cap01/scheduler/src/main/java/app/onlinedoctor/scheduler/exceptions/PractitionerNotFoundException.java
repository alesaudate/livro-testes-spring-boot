package app.onlinedoctor.scheduler.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class PractitionerNotFoundException extends RuntimeException {

    private final UUID practitionerID;

    public PractitionerNotFoundException(UUID practitionerID, Throwable cause) {
        super(cause);
        this.practitionerID = practitionerID;
    }

}
