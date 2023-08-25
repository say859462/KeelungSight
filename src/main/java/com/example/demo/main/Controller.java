package com.example.demo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class Controller {

    private final SightService sightService;

    @Autowired
    public Controller(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping("/SightAPI")
    public ArrayList<Sight> getSight(@RequestParam(name = "zone") String zone) throws IOException {
        ArrayList<Sight> result = sightService.getSightInfo(zone);
        return result;
    }

}
