package com.github.kazuhitom.kazuhitravel.presentation.api.trucking;

import com.github.kazuhitom.kazuhitravel.application.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trucking")
public class TruckingItineraryController {
    private final ItineraryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void trackingItinerary(@RequestBody TruckingGeoRequest request) {
        service.register(request.toItinerary());
    }

    public TruckingItineraryController(ItineraryService service) {
        this.service = service;
    }
}
