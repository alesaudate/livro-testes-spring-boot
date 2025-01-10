package app.onlinedoctor.scheduler.incoming.http.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponseDTO {

    private String message;

}
