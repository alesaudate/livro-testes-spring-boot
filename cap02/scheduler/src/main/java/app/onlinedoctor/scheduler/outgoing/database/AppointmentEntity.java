package app.onlinedoctor.scheduler.outgoing.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   private OffsetDateTime startTime;

   private Duration duration;

   @ManyToOne
   private PatientEntity patient;

   @ManyToOne
   private PractitionerEntity practitioner;

}
