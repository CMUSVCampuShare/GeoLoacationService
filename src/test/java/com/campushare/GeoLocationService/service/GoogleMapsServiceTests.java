package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GoogleMapsServiceTests {
    private GoogleMapsService googleMapsService;
    private DirectionsResponse directionsResponse;

    @BeforeEach
    public void setUp() {
        this.googleMapsService = new GoogleMapsService();
        this.directionsResponse = setDirectionsResponse();
    }

    private DirectionsResponse setDirectionsResponse() {
        String driverAddress = "100 Whisman Station Dr, Mountain View, CA 94043";
        String passengerAddress = "585 Franklin St, Mountain View, CA 94041";
        String schoolAddress = "NASA Research Park, Building 23 Moffett Field, CA 94035";

        Coordinates coordinates = new Coordinates(23.0, 45.0);
        Information duration1 = new Information("9 min", 9);
        Leg leg1 =  new Leg(duration1,new Information(), passengerAddress, coordinates, driverAddress, new Coordinates());

        Information duration2 = new Information("6 min", 6);
        Leg leg2 =  new Leg(duration2,new Information(), schoolAddress, new Coordinates(), passengerAddress, coordinates);

        Leg[] legs =  new Leg[2];
        legs[0] = leg1;
        legs[1] = leg2;

        Route route = new Route(new Bounds(), "Copyright", legs);
        Route[] routes = new Route[1];
        routes[0] = route;

        WayPoint[] wayPoints = new WayPoint[1];

        return new DirectionsResponse(wayPoints, routes);
    }

    @Test
    public void testExtractTravelTime() {

        Long actualDuration = this.googleMapsService.extractTravelTime(this.directionsResponse);

        Assertions.assertEquals(15, actualDuration);
    }

    @Test
    public void testExtractCoordinates() {
        Coordinates expectedCoordinates = new Coordinates(23.0, 45.0);
        Coordinates actualCoordinates = this.googleMapsService.extractCoordinates(this.directionsResponse);

        Assertions.assertEquals(expectedCoordinates, actualCoordinates);
    }
}
