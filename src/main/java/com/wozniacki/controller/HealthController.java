package com.wozniacki.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/health")
public class HealthController {
    @Get("/healthcheck")
    public HttpResponse healthcheck() {
        return HttpResponse.ok();
    }
}
