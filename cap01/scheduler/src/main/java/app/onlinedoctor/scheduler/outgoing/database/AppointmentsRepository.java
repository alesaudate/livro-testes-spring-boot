package app.onlinedoctor.scheduler.outgoing.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentsRepository extends JpaRepository<AppointmentEntity, UUID> {

   List<AppointmentEntity> findByPractitionerIdAndStartTimeBetween(
      UUID practitionerId,
      OffsetDateTime initialTime,
      OffsetDateTime endTime
   );
}
