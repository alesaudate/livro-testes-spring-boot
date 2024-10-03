package app.onlinedoctor.scheduler.incoming.http.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorResponseDTO {

    private String message;

}
