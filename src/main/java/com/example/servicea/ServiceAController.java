package com.example.servicea;

import io.micrometer.tracing.annotation.NewSpan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceAController {
    private final RestTemplate restTemplate;

    @Value("${service.b.url}")
    private String serviceBUrl;

    public ServiceAController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @NewSpan
    @GetMapping("/call-service-b")
    public String callServiceB() {
        return restTemplate.getForObject(serviceBUrl, String.class);
    }


    @NewSpan
    @GetMapping("/response")
    public String response() {
        return "Response from Service A";
    }
}
