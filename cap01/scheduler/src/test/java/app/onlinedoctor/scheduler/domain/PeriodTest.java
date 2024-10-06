package app.onlinedoctor.scheduler.domain;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PeriodTest {

   @Test
   void overlaps_givenTwoPeriodsFullyOverlapping_thenResultIsTrue() {
      // Given
      var periodA = new Period(
         OffsetDateTime.parse("2001-01-01T12:00:00Z"),
         Duration.ofMinutes(30L)
      );
      var periodB = new Period(
         OffsetDateTime.parse("2001-01-01T12:00:00Z"),
         Duration.ofMinutes(30L)
      );

      // When
      var result = periodA.overlaps(periodB);

      // Then
      assertTrue(result);
   }
}