package com.cartservice.cart.services;

import com.cartservice.cart.entities.Cart;
import com.cartservice.cart.entities.Item;

import java.util.List;

public interface ItemService {

    public Item addItem(Item item);

    public boolean deleteItem(int item_id);

    public Item getItem(int item_id) throws ItemNotFoundException;

    public Item addItemToCart(Item item,int cart_id) throws CartNotFoundException;

    public List<Item> getItemsFromTheCart(int cart_id) throws CartNotFoundException;

    public Cart getCartOfItem(int item_id) throws  ItemNotFoundException;

    public int getUnitsOfItem(int item_id,String customerName) throws UserNameNotFoundException, CartNotFoundException;
}
