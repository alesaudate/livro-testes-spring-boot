package app.onlinedoctor.scheduler.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Data
public class Period {

   private final OffsetDateTime start;
   private final Duration duration;

   public boolean overlaps(Period period) {
      var thisStart = start;
      var thisEnd = start.plus(duration);
      return isDateTimeEnclosedInPeriod(thisStart, period)
         || isDateTimeEnclosedInPeriod(thisEnd, period);
   }

   private boolean isDateTimeEnclosedInPeriod(
      OffsetDateTime dateTime, Period period
   ) {
      var startOtherPeriod = period.start;
      var endOtherPeriod = period.start.plus(period.duration);

      var isAfterStart = dateTime.isAfter(startOtherPeriod)
         || dateTime.isEqual(startOtherPeriod);

      var isBeforeEnd = dateTime.isBefore(endOtherPeriod)
         || dateTime.isEqual(endOtherPeriod);

      return (isAfterStart) && (isBeforeEnd);
   }
}
