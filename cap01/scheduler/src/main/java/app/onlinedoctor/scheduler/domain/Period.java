package app.onlinedoctor.scheduler.domain;

import lombok.AllArgsConstructor;
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
        var otherStart = period.start;
        var thisEnd = thisStart.plus(duration);
        var otherEnd = otherStart.plus(period.getDuration());

        return (otherStart.isAfter(thisStart) && otherStart.isBefore(thisEnd))
                || (otherEnd.isBefore(thisEnd) && otherEnd.isAfter(thisStart));
    }
}
