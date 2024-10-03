package app.onlinedoctor.scheduler.incoming.http;

import app.onlinedoctor.scheduler.domain.appointments.AppointmentService;
import app.onlinedoctor.scheduler.incoming.http.dto.AppointmentDTO;
import app.onlinedoctor.scheduler.incoming.http.dto.CreateAppointmentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/appointments",
   consumes = APPLICATION_JSON_VALUE,
   produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AppointmentsAPI {

   private final AppointmentService appointmentService;
   private final AppointmentMapper appointmentMapper;

   @PostMapping
   @ResponseStatus(OK)
   public AppointmentDTO createAppointment(
      @RequestBody CreateAppointmentRequestDTO createAppointmentRequestDTO) {

      var createAppointmentRequest = appointmentMapper
         .mapToDomain(createAppointmentRequestDTO);
      var appointment = appointmentService
         .createAppointment(createAppointmentRequest);
      return appointmentMapper.mapToIncoming(appointment);
   }

   @GetMapping("/{id}")
   public AppointmentDTO findAppointment(@PathVariable("id") UUID id) {
      var appointment = appointmentService.findAppointmentById(id);
      return appointmentMapper.mapToIncoming(appointment);
   }
}
