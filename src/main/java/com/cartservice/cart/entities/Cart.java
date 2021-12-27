package com.cartservice.cart.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;

    @Column(nullable = false,unique = true)
    private String customerName;


    @OneToMany
    @MapKey(name ="itemId")
    private Map<Integer,Item> unitsOfItem;

    public Map<Integer, Item> getUnitsOfItem() {
        return unitsOfItem;
    }

    public void setUnitsOfItem(Map<Integer, Item> unitsOfItem) {
        this.unitsOfItem = unitsOfItem;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customerName='" + customerName + '\'' +
                ", unitsOfItem=" + unitsOfItem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId && Objects.equals(customerName, cart.customerName) && Objects.equals(unitsOfItem, cart.unitsOfItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, customerName, unitsOfItem);
    }

    public int getCartId() {
        return cartId;
    }


    public void setCartId(int cartId) {
        this.cartId = cartId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
