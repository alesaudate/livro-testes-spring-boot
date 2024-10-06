package app.onlinedoctor.scheduler.domain.practitioners;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Practitioner {

    private UUID id;
    private String name;

}
