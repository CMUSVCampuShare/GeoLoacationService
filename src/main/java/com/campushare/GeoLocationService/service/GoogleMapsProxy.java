package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.Trip;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleMapsProxy implements GeoLocationService{

    private Map<Trip, Long> timeCache;
    private GoogleMapsService service;

    public GoogleMapsProxy(GoogleMapsService service) {
        this.service = service;
        this.timeCache = new HashMap<>();
    }
    @Override
    public Long getAddedTime(String origin, String stop, String destination) {
        Trip trip = new Trip(origin, stop, destination);
        if (this.timeCache.containsKey(trip)) {
            System.out.println("Time found in cache!");
            return this.timeCache.get(trip);
        } else {
            System.out.println("Calling Google Maps API");
            Long tripTime = this.service.getAddedTime(origin, stop, destination);
            this.timeCache.put(trip, tripTime);
            return tripTime;
        }

    }

    public void clearCache() {
        this.timeCache = new HashMap<>();
    }
}
