package com.github.kazuhitom.kazuhitravel.infrastructure.datasource.itinerary;

import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.Itinerary;
import com.github.kazuhitom.kazuhitravel.domain.model.itinerary.ItineraryRepository;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItineraryDatasource implements ItineraryRepository {
    private static final String LIST_KEY = "itinerary";
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Itinerary> findAll() {
        return range(0, -1);
    }

    @Override
    public List<Itinerary> findLimitAndAfterOf(long id, long limit) {
        return findLimitOf(limit).stream()
                .filter(i -> i.id() > id)
                .toList();
    }

    @Override
    public List<Itinerary> findLimitOf(long limit) {
        Long size = ofl().size(LIST_KEY);
        if (size == null) return List.of();

        long start = size.longValue() - limit;
        if (start < 0) start = 0;
        return range(start, start + limit);
    }

    @Override
    public void register(Itinerary itinerary) {
        ofl().rightPush(LIST_KEY, itinerary);
    }

    private List<Itinerary> range(long start, long end) {
        return ofl().range(LIST_KEY, start, end)
                .stream()
                .map(i -> (Itinerary) i)
                .toList();

    }

    private ListOperations<String, Object> ofl() {
        return redisTemplate.opsForList();
    }

    public ItineraryDatasource(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
