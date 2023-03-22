package com.github.kazuhitom.kazuhitravel.application.service;

import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.Itinerary;
import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItineraryService {
    private final ItineraryRepository repository;

    private static final long LIMIT_SIZE = 1000;

    public List<Itinerary> of(Long moreThanNotContainId) {
        return Objects.isNull(moreThanNotContainId)
                ? repository.findLimitOf(LIMIT_SIZE)
                : repository.findLimitAndAfterOf(moreThanNotContainId.longValue(), LIMIT_SIZE);
    }

    public void register(Itinerary itinerary) {
        repository.register(itinerary);
    }

    public ItineraryService(ItineraryRepository repository) {
        this.repository = repository;
    }
}
