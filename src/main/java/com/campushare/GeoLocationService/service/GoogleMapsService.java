package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.DirectionsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String DIRECTIONS_API_URL = "https://maps.googleapis.com/maps/api/directions/json";

    public Long getAddedTime(String origin, String stop, String destination) {
        Long timeWithoutStop = calculateTravelTime(origin, destination);
        Long timeWithStop = calculateTravelTime(origin, stop, destination);

        if (timeWithoutStop != null && timeWithStop != null) {
            return timeWithStop - timeWithoutStop;
        } else {
            return null;
        }
    }

    private Long calculateTravelTime(String origin, String destination) {
        String url = String.format("%s?origin=%s&destination=%s&key=%s", DIRECTIONS_API_URL, origin, destination, API_KEY);
        DirectionsResponse response = getDirections(url);
        return extractTravelTime(response);
    }

    private Long calculateTravelTime(String origin, String stop, String destination) {
        String url = String.format("%s?origin=%s&waypoints=%s&destination=%s&key=%s", DIRECTIONS_API_URL, origin, stop, destination, API_KEY);
        DirectionsResponse response = getDirections(url);
        return extractTravelTime(response);
    }

    private DirectionsResponse getDirections(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, DirectionsResponse.class);
    }

    private Long extractTravelTime(DirectionsResponse response) {
        if (response != null && response.getRoutes().length > 0) {
            return response.getRoutes()[0].getLegs()[0].getDuration();
        } else {
            return null;
        }
    }
}