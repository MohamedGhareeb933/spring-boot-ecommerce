package com.fullstack.springbootecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddressId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddressId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    @JsonIgnore
    private Set<OrderItem> orderItems = new HashSet<>();

    public Orders(String orderTrackingNumber, BigDecimal totalPrice, int totalQuantity, String status, Date dateCreated, Date lastUpdated, Address billingAddressId, Address shippingAddressId, Customer customer, Set<OrderItem> orderItems) {
        this.orderTrackingNumber = orderTrackingNumber;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.status = status;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.billingAddressId = billingAddressId;
        this.shippingAddressId = shippingAddressId;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Orders() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Address getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Address billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public Address getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Address shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void add(OrderItem item) {
        if(orderItems == null) {
            orderItems = new HashSet<>();
        }else {
            orderItems.add(item);
            item.setOrders(this);
        }

    }
}
