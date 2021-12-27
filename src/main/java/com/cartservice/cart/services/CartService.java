package com.cartservice.cart.services;

import com.cartservice.cart.entities.Cart;
import com.cartservice.cart.entities.Item;

import java.util.List;

public interface CartService {

    public Cart getCart(int cart_id) throws CartNotFoundException;

    public boolean deleteCart(int cart_id);

    public Cart createCart(Cart cart);

    public String getCustomer(int cart_id) throws CartNotFoundException;

    public Cart getCartOfCustomer(String userName) throws UserNameNotFoundException;

    public Cart updateCart(Cart cart) throws CartNotFoundException;


}
