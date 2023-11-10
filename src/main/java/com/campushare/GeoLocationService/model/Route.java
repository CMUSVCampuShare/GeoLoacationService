package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Route {
    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("copyrights")
    private String copyrights;
    @JsonProperty("legs")
    private Leg[] legs;
}
