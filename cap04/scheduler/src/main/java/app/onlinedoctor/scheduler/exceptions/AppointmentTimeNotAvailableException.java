package app.onlinedoctor.scheduler.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Getter
public class AppointmentTimeNotAvailableException extends RuntimeException{

    private final OffsetDateTime startTime;
    private final Duration duration;


}
