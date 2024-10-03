package app.onlinedoctor.scheduler.domain.patients;

import app.onlinedoctor.scheduler.outgoing.database.PatientEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Patient {

    private UUID id;
    private String name;
    private LocalDate dateOfBirth;
}
