package com.github.kazuhitom.kazuhitravel.domain.model.itinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public record Itinerary(
        @JsonProperty("timestamp")
        LocalDateTime timestamp,
        @JsonProperty("longitude")
        double longitude,
        @JsonProperty("latitude")
        double latitude
) {
    public long id() {
        ZonedDateTime zdtOfJst = timestamp.atZone(ZoneOffset.ofHours(+9));
        return zdtOfJst.toEpochSecond();
    }
}
