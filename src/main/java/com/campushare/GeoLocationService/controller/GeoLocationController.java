package com.campushare.GeoLocationService.controller;

import com.campushare.GeoLocationService.service.GoogleMapsProxy;
import com.campushare.GeoLocationService.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeoLocationController {

    @Autowired
    private GoogleMapsProxy googleMapsProxy;

    @GetMapping("/location")
    @ResponseBody
    public ResponseEntity requestToJoin(@RequestParam String origin, @RequestParam String destination, @RequestParam String stop){
        Long time  = googleMapsProxy.getAddedTime(origin, stop, destination);
        return ResponseEntity.ok(time);
    }
}
