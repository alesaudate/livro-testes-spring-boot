package app.onlinedoctor.scheduler.domain;

import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DateTimeUtilities {

    public static boolean isPeriodOverlappingOtherPeriods(Period period, List<Period> periods) {
        return periods.stream().anyMatch(p -> p.overlaps(period));
    }
}
