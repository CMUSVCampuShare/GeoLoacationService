package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService implements GeoLocationService{
    private static final String API_KEY = "AIzaSyALoxyWDM0Ut92xSQyZyVS_wVDMXV9SUPg";
    private static final String DIRECTIONS_API_URL = "https://maps.googleapis.com/maps/api/directions/json";

    @Override
    public GeoLocationData getGeoLocationData(String origin, String stop, String destination) {
        Long timeWithoutStop = calculateTravelTime(origin, destination);
        GeoLocationData geoLocationDataForStop = calculateTravelTime(origin, stop, destination);
        Long timeWithStop = geoLocationDataForStop.getAddedTime();

        if (timeWithoutStop != null && timeWithStop != null) {
            geoLocationDataForStop.setAddedTime(timeWithStop - timeWithoutStop);
            return geoLocationDataForStop;
        } else {
            return null;
        }
    }

    private Long calculateTravelTime(String origin, String destination) {
        String url = String.format("%s?origin=%s&destination=%s&key=%s", DIRECTIONS_API_URL, origin, destination, API_KEY);
        DirectionsResponse response = getDirections(url);
        return extractTravelTime(response);
    }

    private GeoLocationData calculateTravelTime(String origin, String stop, String destination) {
        String url = String.format("%s?origin=%s&waypoints=%s&destination=%s&key=%s", DIRECTIONS_API_URL, origin, stop, destination, API_KEY);
        DirectionsResponse response = getDirections(url);
        Long timeForTrip = extractTravelTime(response);
        Coordinates pickupPin = extractCoordinates(response);
        GeoLocationData geoLocationData = new GeoLocationData(timeForTrip, pickupPin);
        return geoLocationData;
    }

    private DirectionsResponse getDirections(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, DirectionsResponse.class);
    }

    public Long extractTravelTime(DirectionsResponse response) {
        if (response != null && response.getRoutes().length > 0) {
            Route selectedRoute = response.getRoutes()[0];
            int numberOfLegs = selectedRoute.getLegs().length;

            Long time = 0L;
            for(int i =0; i< numberOfLegs; i++){
                String duration = selectedRoute.getLegs()[i].getDuration().getText();
                String[] splitDuration = duration.split(" ");
                time = time + Long.parseLong(splitDuration[0]);
            }

            return time;
        } else {
            return null;
        }
    }

    public Coordinates extractCoordinates(DirectionsResponse response) {
        if (response != null && response.getRoutes().length > 0) {
            Route selectedRoute = response.getRoutes()[0];
            Leg firstLeg = selectedRoute.getLegs()[0];
            return firstLeg.getDestinationCoordinates();
        }
        else {
            return null;
        }

    }
}