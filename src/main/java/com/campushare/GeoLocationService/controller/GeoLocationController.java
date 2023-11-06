package com.campushare.GeoLocationService.controller;

import com.campushare.GeoLocationService.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeoLocationController {

    @Autowired
    private GoogleMapsService googleMapsService;

    @GetMapping("/location")
    @ResponseBody
    public ResponseEntity requestToJoin(@RequestParam String origin, @RequestParam String destination, @RequestParam String stop){
        Long time  = googleMapsService.getAddedTime(origin, stop, destination);
        return ResponseEntity.ok(time);
    }
}
