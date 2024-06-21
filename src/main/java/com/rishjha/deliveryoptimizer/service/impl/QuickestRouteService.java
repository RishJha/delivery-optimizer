package com.rishjha.deliveryoptimizer.service.impl;

import com.rishjha.deliveryoptimizer.dto.Delivery;
import com.rishjha.deliveryoptimizer.dto.Route;
import com.rishjha.deliveryoptimizer.dto.embedded.Location;
import com.rishjha.deliveryoptimizer.dto.embedded.Node;
import com.rishjha.deliveryoptimizer.service.RouteService;
import com.rishjha.deliveryoptimizer.util.CollectionUtil;
import com.rishjha.deliveryoptimizer.util.DistanceTimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuickestRouteService implements RouteService {

    @Value("${riderSpeed}")
    private double RIDER_SPEED;

    @Override
    public Route createRoute(Location origin, List<Delivery> deliveryNodes) {
        List<Node> nodes = new ArrayList<>();
        for (Delivery delivery : deliveryNodes) {
            nodes.add(delivery.getRestaurant());
            nodes.add(delivery.getCustomer());
        }

        List<List<Node>> permutations = CollectionUtil.getPermutations(nodes);

        double minTime = Double.MAX_VALUE;
        List<Node> bestRoute = new ArrayList<>();
        for (List<Node> permutation : permutations) {
            double time = calculateRouteTime(origin, permutation, deliveryNodes);
            if (time > 0 && time < minTime) {
                minTime = time;
                bestRoute = new ArrayList<>(permutation);
            }
        }

        return new Route(bestRoute, minTime);
    }

    private double calculateRouteTime(Location origin, List<Node> route, List<Delivery> deliveryNodes) {
        double time = 0;
        Location currentLocation = origin;

        // Keep track of pickup times
        boolean[] pickedUp = new boolean[deliveryNodes.size()];

        for (Node node : route) {
            double distance = DistanceTimeUtil.haversineDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    node.getLocation().getLatitude(), node.getLocation().getLongitude());
            time += DistanceTimeUtil.travelTime(distance, RIDER_SPEED);

            for (int i = 0; i < deliveryNodes.size(); i++) {
                Delivery delivery = deliveryNodes.get(i);
                if (node.getName().equals(delivery.getRestaurant().getName())) {
                    pickedUp[i] = true;
                } else if (node.getName().equals(delivery.getCustomer().getName())) {
                    if (!pickedUp[i]) {
                        return -1;  // invalid route
                    }
                    time += delivery.getPrepTime();
                    pickedUp[i] = true;
                }
            }

            currentLocation = node.getLocation();
        }
        return time;
    }

}
