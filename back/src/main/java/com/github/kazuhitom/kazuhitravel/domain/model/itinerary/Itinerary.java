package com.github.kazuhitom.kazuhitravel.domain.model.itinerary;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public record Itinerary(
        LocalDateTime timestamp,
        double longitude,
        double latitude
) {
    public long id() {
        ZonedDateTime zdtOfJst = timestamp.atZone(ZoneOffset.ofHours(+9));
        return zdtOfJst.toEpochSecond();
    }
}
