package com.github.kazuhitom.kazuhitravel.infrastructure.datasource.itinerary;

import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.Itinerary;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class ItineraryDatasource {
    private static final String LIST_KEY = "itinerary";
    private final RedisTemplate<String, Itinerary> redisTemplate;

    public List<Itinerary> findAll() {
        return ofl().range(LIST_KEY, 0, -1);
    }

    public List<Itinerary> findLimitAndAfterOf(long id, long limit) {
        return findLimitOf(limit).stream()
                .filter(i -> i.id() > id)
                .toList();
    }

    public List<Itinerary> findLimitOf(long limit) {
        ListOperations<String, Itinerary> ofl = ofl();
        Long size = ofl.size(LIST_KEY);
        if (size == null) return List.of();

        long start = size.longValue() - limit;
        if (start < 0) start = 0;
        return ofl.range(LIST_KEY, start, start + limit);
    }

    public void register(Itinerary itinerary) {
        ofl().leftPush("LIST_KEY", itinerary);
    }

    private ListOperations<String, Itinerary> ofl() {
        return redisTemplate.opsForList();
    }

    public ItineraryDatasource(RedisTemplate<String, Itinerary> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
