package app.onlinedoctor.scheduler.incoming.messaging.patient;

import app.onlinedoctor.scheduler.domain.patients.PatientService;
import app.onlinedoctor.scheduler.incoming.messaging.MessagePayloadRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientMessageListener {

   private final PatientService patientService;
   private final PatientMapper patientMapper;
   private final MessagePayloadRetriever messagePayloadRetriever;

   @KafkaListener(topics = "data_patient")
   public void listenToPatientMessage(String messageAsString) {
      PatientDTO patientDTO = messagePayloadRetriever.getPayload(messageAsString);
      var patient = patientMapper.mapToDomain(patientDTO);
      patientService.savePatient(patient);
   }
}
