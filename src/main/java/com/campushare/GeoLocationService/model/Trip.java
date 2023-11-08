package com.campushare.GeoLocationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trip {
    private String origin;
    private String stop;
    private String destination;
}
