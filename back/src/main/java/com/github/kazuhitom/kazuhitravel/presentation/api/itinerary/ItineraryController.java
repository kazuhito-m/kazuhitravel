package com.github.kazuhitom.kazuhitravel.presentation.api.itinerary;

import com.github.kazuhitom.kazuhitravel.application.service.ItineraryService;
import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.Itinerary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itinerary")
public class ItineraryController {
    private final ItineraryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItineraryResponse> itineraries(@RequestParam(required = false) Long moreThanNotContainId) {
        List<Itinerary> result = service.of(moreThanNotContainId);
        return result.stream()
                .map(ItineraryResponse::of)
                .toList();
    }

    public ItineraryController(ItineraryService service) {
        this.service = service;
    }
}
