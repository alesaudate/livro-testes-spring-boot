package app.onlinedoctor.scheduler.outgoing.http.patients;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PatientDTO {

   private UUID patientID;
   private String name;
   private LocalDate dateOfBirth;

}
