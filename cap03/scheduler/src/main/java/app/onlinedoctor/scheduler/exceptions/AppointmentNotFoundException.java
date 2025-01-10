package app.onlinedoctor.scheduler.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class AppointmentNotFoundException extends RuntimeException{

    private final UUID appointmentId;
}
