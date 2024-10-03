package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
public class PatientDTO {

    private UUID id;
    private String name;
    private LocalDate dateOfBirth;
}
