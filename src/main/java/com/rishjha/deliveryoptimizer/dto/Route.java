package com.rishjha.deliveryoptimizer.dto;

import com.rishjha.deliveryoptimizer.dto.embedded.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private List<Node> route;

    private Double totalTime;

}
