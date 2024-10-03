package app.onlinedoctor.scheduler.domain.appointments;

import app.onlinedoctor.scheduler.domain.Period;
import app.onlinedoctor.scheduler.domain.patients.PatientService;
import app.onlinedoctor.scheduler.domain.practitioners.PractitionerService;
import app.onlinedoctor.scheduler.exceptions.AppointmentNotFoundException;
import app.onlinedoctor.scheduler.exceptions.AppointmentTimeNotAvailableException;
import app.onlinedoctor.scheduler.outgoing.database.AppointmentEntity;
import app.onlinedoctor.scheduler.outgoing.database.AppointmentsRepository;
import app.onlinedoctor.scheduler.outgoing.messaging.appointment.AppointmentMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static app.onlinedoctor.scheduler.domain.DateTimeUtilities.isPeriodOverlappingOtherPeriods;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PractitionerService practitionerService;
    private final PatientService patientService;
    private final AppointmentsRepository appointmentsRepository;
    private final AppointmentsMapper appointmentsMapper;
    private final AppointmentMessagePublisher appointmentMessagePublisher;

    public Appointment createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        loadPractitioner(createAppointmentRequest);
        loadPatient(createAppointmentRequest);

        verifyThatAppointmentTimeIsAvailableForPractitioner(createAppointmentRequest);

        var appointment = appointmentsMapper.mapToDatabase(createAppointmentRequest);
        appointment = appointmentsRepository.save(appointment);

        var outgoingAppointment = appointmentsMapper.mapToOutgoing(appointment);
        appointmentMessagePublisher.broadcastAppointmentCreated(outgoingAppointment);

        return appointmentsMapper.mapToDomain(appointment);
    }

    public Appointment findAppointmentById(UUID id) {
        var appointment = appointmentsRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));

        return appointmentsMapper.mapToDomain(appointment);
    }


    private void loadPractitioner(CreateAppointmentRequest createAppointmentRequest) {
        var practitionerId = createAppointmentRequest.getPractitionerID();
        var practitioner = practitionerService.findPractitionerById(practitionerId);
        createAppointmentRequest.setPractitioner(practitioner);
    }

    private void loadPatient(CreateAppointmentRequest createAppointmentRequest) {
        var patientID = createAppointmentRequest.getPatientID();
        var patient = patientService.findPatientById(patientID);
        createAppointmentRequest.setPatient(patient);
    }

    private void verifyThatAppointmentTimeIsAvailableForPractitioner(CreateAppointmentRequest createAppointmentRequest) {
        var appointments = loadAppointmentsInGivenDate(createAppointmentRequest);
        checkOverlappingAppointments(appointments, createAppointmentRequest);
    }

    private List<AppointmentEntity> loadAppointmentsInGivenDate(CreateAppointmentRequest createAppointmentRequest) {
        var initialTime = createAppointmentRequest.getStartTime().truncatedTo(ChronoUnit.DAYS);
        var endTime = initialTime.plusDays(1);
        return appointmentsRepository.findByPractitionerIdAndStartTimeBetween(createAppointmentRequest.getPractitionerID(), initialTime, endTime);
    }

    private void checkOverlappingAppointments(List<AppointmentEntity> appointments, CreateAppointmentRequest createAppointmentRequest) {
        var requestedPeriod = new Period(createAppointmentRequest.getStartTime(), createAppointmentRequest.getDuration());
        var periods = appointments.stream().map(appointment -> new Period(appointment.getStartTime(), appointment.getDuration())).toList();
        if (isPeriodOverlappingOtherPeriods(requestedPeriod, periods)) {
            throw new AppointmentTimeNotAvailableException(requestedPeriod.getStart(), requestedPeriod.getDuration());
        }
    }

}
