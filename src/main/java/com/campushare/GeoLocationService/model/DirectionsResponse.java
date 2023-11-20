package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirectionsResponse {
    @JsonProperty("geocoded_waypoints")
    private WayPoint[] wayPoints;
    @JsonProperty("routes")
    private Route[] routes;
}
