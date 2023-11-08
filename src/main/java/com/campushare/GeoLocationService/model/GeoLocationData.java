package com.campushare.GeoLocationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoLocationData {
    private Long addedTime;
    private Coordinates pin;
}
