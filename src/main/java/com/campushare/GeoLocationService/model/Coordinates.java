package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Coordinates {
    @JsonProperty("lng")
    private Double longitude;
    @JsonProperty("lat")
    private Double latitude;
}
