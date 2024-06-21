package com.rishjha.deliveryoptimizer.dto;

import com.rishjha.deliveryoptimizer.dto.embedded.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    private Node customer;
    private Node restaurant;
    private Integer prepTime;
}
