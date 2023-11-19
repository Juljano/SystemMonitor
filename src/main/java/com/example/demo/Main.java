package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Main {


    @GetMapping("/")
    public int getVariable() {
        return 1 + 1;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        new SensorState().getSensorStates();
        new SensorState().getDiskInfo();
        new SensorState().getOperationSystemInfo();
        new SensorState().getNetworkInfo();
    }

}
