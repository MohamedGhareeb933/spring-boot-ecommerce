package com.fullstack.springbootecommerce.service;

import com.fullstack.springbootecommerce.dao.CustomerRepository;
import com.fullstack.springbootecommerce.dao.ProductRepository;
import com.fullstack.springbootecommerce.dto.JBody;
import com.fullstack.springbootecommerce.dto.ResponseMessage;
import com.fullstack.springbootecommerce.entity.Customer;
import com.fullstack.springbootecommerce.entity.Orders;
import com.fullstack.springbootecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class EntityServiceImple implements EntityService {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    private Product product;
    private BigDecimal totalPrice;
    private int totalQuantity;

    @Autowired
    EntityServiceImple(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository =  customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ResponseMessage addOrder(JBody body) {

        int debugSize = body.getOrderItems().size();

        Orders orders = new Orders();
        Customer customer = body.getCustomer();
        totalPrice = new BigDecimal(0);

        final String generatedUUID = generateUUID();

        body.getOrderItems().forEach(item -> {
            product = productRepository.findById(item.getProductId()).get();

            totalQuantity += item.getQuantity();
            totalPrice = totalPrice.add(product.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));

            orders.add(item);
        });


        orders.setBillingAddressId(body.getBillingAddress());
        orders.setShippingAddressId(body.getShippingAddress());

        orders.setTotalQuantity(totalQuantity);
        orders.setTotalPrice(totalPrice);

        orders.setOrderTrackingNumber(generatedUUID);

        customer.add(orders);
        customerRepository.save(customer);

        return new ResponseMessage(generatedUUID);
    };

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
