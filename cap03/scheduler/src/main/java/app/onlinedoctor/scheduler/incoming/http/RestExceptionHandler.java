package app.onlinedoctor.scheduler.incoming.http;

import app.onlinedoctor.scheduler.exceptions.AppointmentNotFoundException;
import app.onlinedoctor.scheduler.exceptions.AppointmentTimeNotAvailableException;
import app.onlinedoctor.scheduler.exceptions.ProxyRequestFailureException;
import app.onlinedoctor.scheduler.exceptions.PatientNotFoundException;
import app.onlinedoctor.scheduler.exceptions.PractitionerNotFoundException;
import app.onlinedoctor.scheduler.incoming.http.dto.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePatientNotFoundException(PatientNotFoundException patientNotFoundException) {
        var errorResponseDTO = makeErrorResponseDTO(
            "Patient with ID " + patientNotFoundException.getPatientId() + " not found."
        );
        return ResponseEntity.status(NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(PractitionerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePractitionerNotFoundException(PractitionerNotFoundException practitionerNotFoundException) {
        var errorResponseDTO = makeErrorResponseDTO(
                "Practitioner with ID " + practitionerNotFoundException.getPractitionerID() + " not found."
        );
        return ResponseEntity.status(NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAppointmentNotFoundException(AppointmentNotFoundException appointmentNotFoundException) {
        var errorResponseDTO = makeErrorResponseDTO(
                "Appointment with ID " + appointmentNotFoundException.getAppointmentId() + " not found."
        );
        return ResponseEntity.status(NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(AppointmentTimeNotAvailableException.class)
    public ResponseEntity<ErrorResponseDTO> handleAppointmentTimeNotAvailableException(AppointmentTimeNotAvailableException appointmentTimeNotAvailableException) {
        var errorResponseDTO = makeErrorResponseDTO(
                buildAppointmentTimeNotAvailableMessage(appointmentTimeNotAvailableException)
        );
        return ResponseEntity.status(CONFLICT).body(errorResponseDTO);
    }

    @ExceptionHandler(ProxyRequestFailureException.class)
    public ResponseEntity<ErrorResponseDTO> handleProxyRequestFailureException(ProxyRequestFailureException proxyRequestFailureException) {

        var errorResponseDTO = makeErrorResponseDTO(
                "Failure to communicate with external service: " + proxyRequestFailureException.getExternalServiceName()
        );
        return ResponseEntity.status(BAD_GATEWAY).body(errorResponseDTO);
    }

    private String buildAppointmentTimeNotAvailableMessage(AppointmentTimeNotAvailableException appointmentTimeNotAvailableException) {
        return String.format("Requested appointment period is not available. Start time: %s . Duration: %s",
                appointmentTimeNotAvailableException.getStartTime(),
                appointmentTimeNotAvailableException.getDuration());
    }

    private ErrorResponseDTO makeErrorResponseDTO(String message) {
        return ErrorResponseDTO.builder()
                .message(message)
                .build();
    }
}
