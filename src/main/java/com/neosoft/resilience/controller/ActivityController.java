package com.neosoft.resilience.controller;

import com.neosoft.resilience.model.Activity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final RestTemplate restTemplate;

    private final String BORED_API = "https://www.boredapi.com/api/activity";

    @GetMapping
    @CircuitBreaker(name = "randomActivity", fallbackMethod = "fallbackRandomActivity")
    public String getRandomActivity() {
        ResponseEntity<Activity> responseEntity = restTemplate.getForEntity(BORED_API, Activity.class);
        Activity activity = responseEntity.getBody();
        log.info("Activity received: " + activity.getActivity());
        return activity.getActivity();
    }

    public String fallbackRandomActivity(Throwable throwable) {
        log.info("fallback method: error: {}", throwable.getMessage());
        return "FallBackMethod";
    }

}
