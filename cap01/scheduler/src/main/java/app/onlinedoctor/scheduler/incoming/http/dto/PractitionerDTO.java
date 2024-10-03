package app.onlinedoctor.scheduler.incoming.http.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PractitionerDTO {

    private final UUID id;
    private final String name;
}
