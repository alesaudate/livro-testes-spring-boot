package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateAppointmentRequestDTO {


    private final OffsetDateTime startTime;
    private final Duration duration;
    private final UUID patientID;
    private final UUID practitionerID;


}
