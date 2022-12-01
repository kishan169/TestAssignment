package com.project.orderservice.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orders {

    @Id
    private Integer orderId;
    private Integer customerId;
    private LocalDate orderDate;
    private String productName;
    private Double price;
    private Double discount_price;
    private Double discount_percentage;

}
