package app.onlinedoctor.scheduler.outgoing.http.practitioners;

import lombok.Data;

import java.util.UUID;

@Data
public class PractitionerDTO {

    private UUID id;
    private String name;
}
