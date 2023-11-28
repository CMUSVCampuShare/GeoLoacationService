package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.GeoLocationData;
import com.campushare.GeoLocationService.model.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleMapsProxy implements GeoLocationService{

    private Map<Trip, GeoLocationData> geoLocationCache;
    private GoogleMapsService service;

    private LocalDate lastRefresh;

    public GoogleMapsProxy(GoogleMapsService service) {
        this.service = service;
        this.geoLocationCache = new HashMap<>();
        this.lastRefresh = LocalDate.now();
    }
    @Override
    public GeoLocationData getGeoLocationData(String origin, String stop, String destination) {
        LocalDate today = LocalDate.now();
        Boolean tooOld = this.lastRefresh.isBefore(today.minusDays(30));
        if (tooOld) {
            clearCache();
        }
        Trip trip = new Trip(origin, stop, destination);
        if (this.geoLocationCache.containsKey(trip)) {
            System.out.println("Time found in cache!");
            return this.geoLocationCache.get(trip);
        } else {
            System.out.println("Calling Google Maps API");
            GeoLocationData tripTime = this.service.getGeoLocationData(origin, stop, destination);
            if (tripTime.getAddedTime() < 10) {
                this.geoLocationCache.put(trip, tripTime);
            }
            return tripTime;
        }

    }

    public void clearCache() {
        this.geoLocationCache = new HashMap<>();
        this.lastRefresh = LocalDate.now();
    }

    public int getCacheLength() {
        return this.geoLocationCache.size();
    }
}
