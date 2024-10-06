package app.onlinedoctor.scheduler.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodTest {

   @ParameterizedTest
   @MethodSource("testDataForOverlaps")
   void overlaps_givenTwoPeriods_thenCheckResultIsExpected(
      Period periodA, Period periodB, boolean expectedResult
   ) {
      // When
      var result = periodA.overlaps(periodB);

      // Then
      assertEquals(expectedResult, result);
   }

   public static List<Arguments> testDataForOverlaps() {
      return List.of(
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2001-01-01T12:00:00", 30),
            true
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2001-01-01T12:01:00", 29),
            true
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2001-01-01T12:00:00", 29),
            true
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2001-01-01T12:29:59", 1),
            true
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 60 * 24),
            makePeriod("2001-01-02T11:00:00", 30),
            true
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2001-01-01T12:30:00", 10),
            false
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2001-01-02T12:00:00", 30),
            false
         ),
         Arguments.of(
            makePeriod("2001-01-01T12:00:00", 30),
            makePeriod("2002-01-01T12:00:00", 30),
            false
         )
      );
   }

   private static Period makePeriod(String dateTime, int minutes) {
      return new Period(
         OffsetDateTime.parse(dateTime + "Z"),
         Duration.ofMinutes(minutes)
      );
   }
}