package com.campushare.GeoLocationService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Information {
    @JsonProperty("text")
    private String text;
    @JsonProperty("value")
    private int value;
}
