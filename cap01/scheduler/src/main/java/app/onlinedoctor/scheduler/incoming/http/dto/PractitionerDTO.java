package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class PractitionerDTO {

   private final UUID id;
   private final String name;
}
