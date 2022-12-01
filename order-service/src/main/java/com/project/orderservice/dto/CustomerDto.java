package com.project.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CustomerDto {

    @NotNull
    private Integer customerId;

    @NotNull
    private String name;

    @NotNull
    @Size(min=10,max=10,message = "mobile number should be in 10 digit")
    private String mobile;

    @NotNull
    private String password;
}
