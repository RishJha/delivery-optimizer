package com.rishjha.deliveryoptimizer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishjha.deliveryoptimizer.dto.BatchedDelivery;
import com.rishjha.deliveryoptimizer.dto.Route;
import com.rishjha.deliveryoptimizer.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("v1/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping
    private ResponseEntity<Route> createRoute(@RequestBody BatchedDelivery batchedDelivery) {
        Route route = routeService.createRoute(batchedDelivery.getAgent().getLocation(), batchedDelivery.getDeliveries());
        return ResponseEntity.ok(route);
    }

    @GetMapping("test")
    private ResponseEntity<Route> testRoute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource("classpath:batchedDelivery.json");
        InputStream inputStream = resource.getInputStream();
        BatchedDelivery batchedDelivery = mapper.readValue(inputStream, BatchedDelivery.class);
        return createRoute(batchedDelivery);
    }

}
