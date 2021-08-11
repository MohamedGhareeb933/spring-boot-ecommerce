package com.fullstack.springbootecommerce.dto;

import com.fullstack.springbootecommerce.entity.Address;
import com.fullstack.springbootecommerce.entity.Customer;
import com.fullstack.springbootecommerce.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data @Setter @Getter
public class JBody {
    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
    private Set<OrderItem> orderItems = new HashSet<>();
}
