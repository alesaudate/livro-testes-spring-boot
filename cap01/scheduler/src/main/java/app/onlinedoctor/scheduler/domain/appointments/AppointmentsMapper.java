package app.onlinedoctor.scheduler.domain.appointments;

import app.onlinedoctor.scheduler.domain.patients.PatientMapper;
import app.onlinedoctor.scheduler.domain.practitioners.PractitionerMapper;
import app.onlinedoctor.scheduler.outgoing.database.AppointmentEntity;
import app.onlinedoctor.scheduler.outgoing.messaging.appointment.AppointmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentsMapper {

    private final PatientMapper patientMapper;
    private final PractitionerMapper practitionerMapper;

    public AppointmentEntity mapToDatabase(
          CreateAppointmentRequest createAppointmentRequest) {

        var patient = createAppointmentRequest.getPatient();
        var practitioner = createAppointmentRequest.getPractitioner();

        return AppointmentEntity.builder()
                .duration(createAppointmentRequest.getDuration())
                .startTime(createAppointmentRequest.getStartTime())
                .patient(patientMapper.mapToDatabase(patient))
                .practitioner(practitionerMapper.mapToDatabase(practitioner))
                .build();
    }


    public Appointment mapToDomain(AppointmentEntity appointmentEntity) {
        var patient = appointmentEntity.getPatient();
        var practitioner = appointmentEntity.getPractitioner();

        return Appointment.builder()
                .duration(appointmentEntity.getDuration())
                .patient(patientMapper.mapToDomain(patient))
                .startTime(appointmentEntity.getStartTime())
                .practitioner(practitionerMapper.mapToDomain(practitioner))
                .build();
    }

    public AppointmentDTO mapToOutgoing(AppointmentEntity appointmentEntity) {
        return AppointmentDTO.builder()
                .appointmentId(appointmentEntity.getId())
                .duration(appointmentEntity.getDuration())
                .patientID(appointmentEntity.getPatient().getId())
                .practitionerID(appointmentEntity.getPractitioner().getId())
                .startTime(appointmentEntity.getStartTime())
                .build();
    }

}
