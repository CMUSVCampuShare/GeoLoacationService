package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WayPoint {
    @JsonProperty("geocoder_status")
    private String geoCodeStatus;
    @JsonProperty("partial_match")
    private Boolean partialMatch;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("types")
    private String[] types;
}
