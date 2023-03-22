package com.github.kazuhitom.kazuhitravel.domain.model.itinerary;

import java.util.List;

public interface ItineraryRepository {
    List<Itinerary> findAll();

    List<Itinerary> findLimitAndAfterOf(long id, long limit);

    List<Itinerary> findLimitOf(long limit);

    void register(Itinerary itinerary);
}
