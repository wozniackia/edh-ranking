package com.wozniacki.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/health")
public class HealthController {
    @Get
    public HttpResponse healthcheck() {
        return HttpResponse.ok();
    }
}
