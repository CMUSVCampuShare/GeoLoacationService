package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.Coordinates;
import com.campushare.GeoLocationService.model.GeoLocationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class GoogleMapsProxyTests {
    private GoogleMapsService googleMapsService;
    private GoogleMapsProxy googleMapsProxy;

    @BeforeEach
    public void setUp() {
        this.googleMapsService = mock(GoogleMapsService.class);
        this.googleMapsProxy = new GoogleMapsProxy(googleMapsService);
    }
    @Test
    public void proxyCallsGoogleMapsServiceWhenCacheIsEmpty(){
        String driverAddress = "100 Whisman Station Dr, Mountain View, CA 94043";
        String passengerAddress = "585 Franklin St, Mountain View, CA 94041";
        String schoolAddress = "NASA Research Park, Building 23 Moffett Field, CA 94035";

        Assertions.assertEquals(0, this.googleMapsProxy.getCacheLength());

        Coordinates pin = new Coordinates();
        GeoLocationData expectedData = new GeoLocationData(6L, pin);

        when(googleMapsService.getGeoLocationData(any(), any(), any())).thenReturn(expectedData);
        GeoLocationData data = this.googleMapsProxy.getGeoLocationData(driverAddress, passengerAddress, schoolAddress);

        Assertions.assertEquals(data, expectedData);
        Assertions.assertEquals(1, this.googleMapsProxy.getCacheLength());
    }

    @Test
    public void proxyReturnsCachedValue(){
        String driverAddress = "100 Whisman Station Dr, Mountain View, CA 94043";
        String passengerAddress = "585 Franklin St, Mountain View, CA 94041";
        String schoolAddress = "NASA Research Park, Building 23 Moffett Field, CA 94035";

        Assertions.assertEquals(0, this.googleMapsProxy.getCacheLength());

        Coordinates pin = new Coordinates();
        GeoLocationData expectedData = new GeoLocationData(6L, pin);

        when(googleMapsService.getGeoLocationData(any(), any(), any())).thenReturn(expectedData);
        GeoLocationData data = this.googleMapsProxy.getGeoLocationData(driverAddress, passengerAddress, schoolAddress);

        Assertions.assertEquals(data, expectedData);
        Assertions.assertEquals(1, this.googleMapsProxy.getCacheLength());

        GeoLocationData newAPIData = new GeoLocationData(9L, pin);
        when(googleMapsService.getGeoLocationData(any(), any(), any())).thenReturn(newAPIData);
        data = this.googleMapsProxy.getGeoLocationData(driverAddress, passengerAddress, schoolAddress);

        Assertions.assertEquals(data, expectedData);
        Assertions.assertEquals(1, this.googleMapsProxy.getCacheLength());
    }
}
