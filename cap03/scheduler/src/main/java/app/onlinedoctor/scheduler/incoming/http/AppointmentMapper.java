package app.onlinedoctor.scheduler.incoming.http;

import app.onlinedoctor.scheduler.domain.appointments.Appointment;
import app.onlinedoctor.scheduler.domain.appointments.CreateAppointmentRequest;
import app.onlinedoctor.scheduler.incoming.http.dto.AppointmentDTO;
import app.onlinedoctor.scheduler.incoming.http.dto.CreateAppointmentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("incomingAppointmentMapper")
@RequiredArgsConstructor
public class AppointmentMapper {

   private final PatientMapper patientMapper;
   private final PractitionerMapper practitionerMapper;

   public CreateAppointmentRequest mapToDomain(
      CreateAppointmentRequestDTO createAppointmentRequestDTO
   ) {

      return CreateAppointmentRequest.builder()
         .startTime(createAppointmentRequestDTO.getStartTime())
         .patientID(createAppointmentRequestDTO.getPatientID())
         .duration(createAppointmentRequestDTO.getDuration())
         .practitionerID(createAppointmentRequestDTO.getPractitionerID())
         .build();
   }

   public AppointmentDTO mapToIncoming(Appointment appointment) {
      var practitioner = appointment.getPractitioner();
      var patient = appointment.getPatient();

      return AppointmentDTO.builder()
         .duration(appointment.getDuration())
         .startTime(appointment.getStartTime())
         .patientDTO(patientMapper.mapToIncoming(patient))
         .practitionerDTO(practitionerMapper.mapToIncoming(practitioner))
         .build();
   }
}
