package com.vaga.java.java8.stream.kata.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "mall")
public class OnlineShoppingMall {

    private List<Shop> shopList;
    private List<Customer> customerList;


    @XmlElementWrapper(name="shops")
    @XmlElement(name="shop")
    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @XmlElementWrapper(name="customers")
    @XmlElement(name="customer")
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
