package com.cartservice.cart.services.impl;

import com.cartservice.cart.daos.CartDao;
import com.cartservice.cart.entities.Cart;
import com.cartservice.cart.services.CartNotFoundException;
import com.cartservice.cart.services.CartService;
import com.cartservice.cart.services.UserNameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Cart getCart(int cart_id) throws CartNotFoundException {

        return cartDao.findById(cart_id).orElseThrow(() -> new CartNotFoundException());
    }

    @Override
    public boolean deleteCart(int cart_id) {
        cartDao.deleteById(cart_id);
        return  true;
    }

    @Override
    public Cart createCart(Cart cart) {
        cartDao.save(cart);
        return cart;
    }

    @Override
    public String getCustomer(int cart_id) throws CartNotFoundException {

        Cart savedCart = cartDao.findById(cart_id).orElseThrow(() -> new CartNotFoundException());
        return savedCart.getCustomerName();
    }

    @Override
    public Cart getCartOfCustomer(String customerName) throws UserNameNotFoundException {

        Cart savedCart = cartDao.findByCustomerName(customerName);
        if(savedCart == null) {
            Cart cart = new Cart();
            cart.setCustomerName(customerName);
            cartDao.save(cart);
            return  cart;
        }
            return savedCart;

    }

    @Override
    public Cart updateCart(Cart cart) throws CartNotFoundException {

        Cart savedCart = this.getCart(cart.getCartId());
        Map<Integer,Item> map = new HashMap<Integer,item>()
        savedCart.setUnitsOfItem(cart.getUnitsOfItem());
        savedCart.setCustomerName(cart.getCustomerName());
        cartDao.save(savedCart);
        return savedCart;
    }


}
