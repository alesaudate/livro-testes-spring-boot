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

      var otherStart = period.start;
      var otherEnd = period.start.plus(period.duration);

      return
         thisStart.isEqual(otherStart) ||
            contains(otherStart, thisStart, thisEnd) ||
            contains(otherEnd, thisStart, thisEnd);
   }

   private boolean contains(
      OffsetDateTime dateTime, OffsetDateTime start,
      OffsetDateTime end
   ) {
      var isAfterStart = dateTime.isAfter(start);
      var isBeforeEnd = dateTime.isBefore(end);
      return isAfterStart && isBeforeEnd;
   }
}
