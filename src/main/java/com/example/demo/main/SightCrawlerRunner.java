package com.example.demo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SightCrawlerRunner implements ApplicationRunner {
    private final KeelungSightsCrawler crawler;
    private final SightService sightService;

    @Autowired
    public SightCrawlerRunner(SightRepository repository) throws IOException {
        this.crawler = new KeelungSightsCrawler();
        this.sightService = new SightService(repository);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        String[] zone = {"中山區", "信義區", "仁愛區", "中正區", "安樂區", "七堵區", "暖暖區"};
        for (int i = 0; i < zone.length; i++) {
            this.sightService.addSightInfo(this.crawler.getItems(zone[i]), zone[i]);
        }
    }
}
