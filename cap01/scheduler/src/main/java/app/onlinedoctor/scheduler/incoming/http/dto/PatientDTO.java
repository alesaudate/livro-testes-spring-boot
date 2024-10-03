package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PatientDTO {

   private final UUID id;
   private final String name;
   private final LocalDate dateOfBirth;
}
