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

        Coordinates coordinates = new Coordinates();
        coordinates.setLongitude(23.0);
        coordinates.setLatitude(45.0);

        Information duration1 = new Information();
        duration1.setText("9 min");
        duration1.setValue(9);

        Leg leg1 =  new Leg();
        leg1.setDuration(duration1);
        leg1.setDistance(new Information());
        leg1.setDestinationAddress(passengerAddress);
        leg1.setDestinationCoordinates(coordinates);
        leg1.setStartAddress(driverAddress);
        leg1.setStartCoordinates(new Coordinates());

        Information duration2 = new Information();
        duration2.setText("6 min");
        duration2.setValue(6);

        Leg leg2 =  new Leg();
        leg2.setDuration(duration2);
        leg2.setDistance(new Information());
        leg2.setDestinationAddress(schoolAddress);
        leg2.setDestinationCoordinates(new Coordinates());
        leg2.setStartAddress(passengerAddress);
        leg2.setStartCoordinates(coordinates);

        Leg[] legs =  new Leg[2];
        legs[0] = leg1;
        legs[1] = leg2;

        Route route = new Route();
        route.setBounds(new Bounds());
        route.setCopyrights("Copyright");
        route.setLegs(legs);

        Route[] routes = new Route[1];
        routes[0] = route;

        WayPoint[] wayPoints = new WayPoint[1];

        DirectionsResponse directionsResponse1 = new DirectionsResponse();
        directionsResponse1.setWayPoints(wayPoints);
        directionsResponse1.setRoutes(routes);

        return directionsResponse1;
    }

    @Test
    public void testExtractTravelTime() {

        Long actualDuration = this.googleMapsService.extractTravelTime(this.directionsResponse);

        Assertions.assertEquals(15, actualDuration);
    }

    @Test
    public void testExtractCoordinates() {
        Coordinates expectedCoordinates = new Coordinates();
        expectedCoordinates.setLatitude(45.0);
        expectedCoordinates.setLongitude(23.0);
        Coordinates actualCoordinates = this.googleMapsService.extractCoordinates(this.directionsResponse);

        Assertions.assertEquals(expectedCoordinates, actualCoordinates);
    }
}
