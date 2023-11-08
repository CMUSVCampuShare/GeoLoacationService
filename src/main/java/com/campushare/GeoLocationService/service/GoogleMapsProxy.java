package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.GeoLocationData;
import com.campushare.GeoLocationService.model.Trip;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleMapsProxy implements GeoLocationService{

    private Map<Trip, GeoLocationData> geoLocationCache;
    private GoogleMapsService service;

    public GoogleMapsProxy(GoogleMapsService service) {
        this.service = service;
        this.geoLocationCache = new HashMap<>();
    }
    @Override
    public GeoLocationData getGeoLocationData(String origin, String stop, String destination) {
        Trip trip = new Trip(origin, stop, destination);
        if (this.geoLocationCache.containsKey(trip)) {
            System.out.println("Time found in cache!");
            return this.geoLocationCache.get(trip);
        } else {
            System.out.println("Calling Google Maps API");
            GeoLocationData tripTime = this.service.getGeoLocationData(origin, stop, destination);
            this.geoLocationCache.put(trip, tripTime);
            return tripTime;
        }

    }

    public void clearCache() {
        this.geoLocationCache = new HashMap<>();
    }
}
