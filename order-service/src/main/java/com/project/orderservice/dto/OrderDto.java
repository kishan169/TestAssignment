package com.project.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderDto {

    @NotNull
    private Integer orderId;
    private String orderDate;
    private String productName;
    private Double price;
}
