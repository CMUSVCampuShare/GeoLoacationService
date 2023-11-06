package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Leg {
    @JsonProperty("duration")
    private Information duration;
    @JsonProperty("distance")
    private Information distance;
    @JsonProperty("end_address")
    private String destinationAddress;
    @JsonProperty("end_location")
    private Coordinates destinationCoordinates;
    @JsonProperty("start_address")
    private  String startAddress;
    @JsonProperty("start_location")
    private Coordinates startCoordinates;

}
