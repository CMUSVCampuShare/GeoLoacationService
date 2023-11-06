package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bounds {
    @JsonProperty("northeast")
    private Coordinates northeast;
    @JsonProperty("southwest")
    private Coordinates southwest;
}
