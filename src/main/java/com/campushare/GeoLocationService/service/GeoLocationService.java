package com.campushare.GeoLocationService.service;

import com.campushare.GeoLocationService.model.GeoLocationData;

public interface GeoLocationService {
    GeoLocationData getGeoLocationData(String origin, String stop, String destination);
}
