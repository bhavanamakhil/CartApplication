package com.cartservice.cart.daos;

import com.cartservice.cart.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart,Integer> {

    public Cart findByCustomerName(String customerName);

}
