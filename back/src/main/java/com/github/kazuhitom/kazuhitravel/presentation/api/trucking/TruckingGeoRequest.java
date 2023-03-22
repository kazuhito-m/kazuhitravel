package com.github.kazuhitom.kazuhitravel.presentation.api.trucking;

import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.Itinerary;

import java.time.LocalDateTime;

public record TruckingGeoRequest(
        double longitude,
        double latitude
) {
    public Itinerary toItinerary() {
        return new Itinerary(
                LocalDateTime.now(),
                longitude,
                latitude
        );
    }
}
