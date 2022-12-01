package com.project.orderservice.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    private Integer customerId;

    private String name;

    private String mobile;
    private String password;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Transient
    private List<Orders> orders = new ArrayList<>();

    public Customer(Integer customerId, String name, String mobile, String password, Category category) {
        this.customerId = customerId;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.category = category;
    }
}
