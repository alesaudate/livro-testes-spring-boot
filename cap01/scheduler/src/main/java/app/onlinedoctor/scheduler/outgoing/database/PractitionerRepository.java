package app.onlinedoctor.scheduler.outgoing.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PractitionerRepository extends JpaRepository<PractitionerEntity, UUID> {
}
