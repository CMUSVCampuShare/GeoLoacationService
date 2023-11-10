package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DirectionsResponse {
    @JsonProperty("geocoded_waypoints")
    private WayPoint[] wayPoints;
    @JsonProperty("routes")
    private Route[] routes;
}
