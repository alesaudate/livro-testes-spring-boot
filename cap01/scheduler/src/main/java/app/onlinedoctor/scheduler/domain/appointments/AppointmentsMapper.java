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

    public AppointmentEntity mapToDatabaseEntity(CreateAppointmentRequest createAppointmentRequest) {
        return AppointmentEntity.builder()
                .duration(createAppointmentRequest.getDuration())
                .startTime(createAppointmentRequest.getStartTime())
                .patient(createAppointmentRequest.getPatient().toEntity())
                .practitioner(practitionerMapper.mapToEntity(createAppointmentRequest.getPractitioner()))
                .build();
    }


    public Appointment mapToDomain(AppointmentEntity appointmentEntity) {
        return Appointment.builder()
                .duration(appointmentEntity.getDuration())
                .patient(patientMapper.mapToDomain(appointmentEntity.getPatient()))
                .startTime(appointmentEntity.getStartTime())
                .practitioner(practitionerMapper.mapToDomain(appointmentEntity.getPractitioner()))
                .build();
    }

    public AppointmentDTO mapToOutgoingAppointmentDTO(AppointmentEntity appointmentEntity) {
        return AppointmentDTO.builder()
                .appointmentId(appointmentEntity.getId())
                .duration(appointmentEntity.getDuration())
                .patientID(appointmentEntity.getPatient().getId())
                .practitionerID(appointmentEntity.getPractitioner().getId())
                .startTime(appointmentEntity.getStartTime())
                .build();
    }

}
