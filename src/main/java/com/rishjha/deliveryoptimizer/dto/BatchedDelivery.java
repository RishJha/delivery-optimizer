package com.rishjha.deliveryoptimizer.dto;

import com.rishjha.deliveryoptimizer.dto.embedded.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchedDelivery {
    private Node agent;
    private List<Delivery> deliveries;
}
