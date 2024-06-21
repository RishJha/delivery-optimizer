package com.rishjha.deliveryoptimizer.service;

import com.rishjha.deliveryoptimizer.dto.Delivery;
import com.rishjha.deliveryoptimizer.dto.Route;
import com.rishjha.deliveryoptimizer.dto.embedded.Location;

import java.util.List;

public interface RouteService {

    Route createRoute(Location origin, List<Delivery> deliveryNodes);

}
