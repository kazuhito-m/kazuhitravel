package com.github.kazuhitom.kazuhitravel.presentation.api.itinerary;

import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.Itinerary;

import java.time.LocalDateTime;

public record ItineraryResponse(
        long id,
        LocalDateTime timestamp,
        double longitude,
        double latitude
) {
    public static ItineraryResponse of(Itinerary i) {
        return new ItineraryResponse(
                i.id(),
                i.timestamp(),
                i.longitude(),
                i.latitude()
        );
    }
}
