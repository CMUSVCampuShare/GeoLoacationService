package com.campushare.GeoLocationService.controller;
import com.campushare.GeoLocationService.model.Coordinates;
import com.campushare.GeoLocationService.model.GeoLocationData;
import com.campushare.GeoLocationService.service.GoogleMapsProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GeoLocationControllerTests {

    @Mock
    private GoogleMapsProxy googleMapsProxy;

    @InjectMocks
    private GeoLocationController geoLocationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getGeoLocationData_returnsGeoLocationData() {
        Coordinates mockCoordinates = new Coordinates();
        mockCoordinates.setLatitude(33.5);
        mockCoordinates.setLongitude(33.5);
        GeoLocationData mockGeoLocationData = new GeoLocationData(8L,mockCoordinates);

        when(googleMapsProxy.getGeoLocationData(any(), any(), any())).thenReturn(mockGeoLocationData);

        ResponseEntity<GeoLocationData> response = geoLocationController.getGeoLocationData("origin", "destination", "stop");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockGeoLocationData, response.getBody());
        verify(googleMapsProxy, times(1)).getGeoLocationData(any(), any(), any());
    }
}
