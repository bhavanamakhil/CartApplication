package com.cartservice.cart.daos;

import com.cartservice.cart.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemDao extends JpaRepository<Item,Integer> {

    public List<Item> findByCart(int cart_id);
}
