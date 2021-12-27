package com.cartservice.cart.services.impl;

import com.cartservice.cart.daos.CartDao;
import com.cartservice.cart.daos.ItemDao;
import com.cartservice.cart.entities.Cart;
import com.cartservice.cart.entities.Item;
import com.cartservice.cart.services.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CartService cartService;

    @Override
    public Item addItem(Item item) {

        itemDao.save(item);
        return item;
    }

    @Override
    public boolean deleteItem(int item_id) {

        itemDao.deleteById(item_id);
        return true;
    }

    @Override
    public Item getItem(int item_id) throws ItemNotFoundException {

        return itemDao.findById(item_id).orElseThrow(() ->new ItemNotFoundException());
    }

    @Override
    public Item addItemToCart(Item item, int cart_id) throws CartNotFoundException {

        Cart savedCart = cartService.getCart(cart_id);
        item.setCart(savedCart);
        itemDao.save(item);
        return item;
     }

    @Override
    public List<Item> getItemsFromTheCart(int cart_id) throws CartNotFoundException {

//        Cart savedCart = cartService.getCart(cart_id); Lazy Initialization
//        List<Item> items = savedCart.getItem();
        List<Item> items = itemDao.findByCart(cart_id);
        return items;
    }

    @Override
    public Cart getCartOfItem(int item_id) throws ItemNotFoundException {

        Item item = this.getItem(item_id);
        return item.getCart();
    }

    @Override
    public int getUnitsOfItem(int item_id, String customerName) throws UserNameNotFoundException, CartNotFoundException {

        Cart savedCart = cartService.getCartOfCustomer(customerName);
        List<Item> items = this.getItemsFromTheCart(savedCart.getCartId());
        int count = 0;
        for(Item item:items) {
            if(item.getItemId() == item_id){
                count++;
            }
        }

        return count;
    }


}
