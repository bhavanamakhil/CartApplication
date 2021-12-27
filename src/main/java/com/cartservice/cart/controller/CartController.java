package com.cartservice.cart.controller;


import com.cartservice.cart.entities.Cart;
import com.cartservice.cart.entities.Item;
import com.cartservice.cart.services.CartNotFoundException;
import com.cartservice.cart.services.CartService;
import com.cartservice.cart.services.ItemService;
import com.cartservice.cart.services.UserNameNotFoundException;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/carts")
public class CartController {

    private static Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity greetings() {

        return new ResponseEntity("Hello Everyone", HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity getCart(@PathVariable(name="cartId") int cartId) throws CartNotFoundException {

        Cart cart = cartService.getCart(cartId);

        return new ResponseEntity(cart,HttpStatus.OK);
    }

    @PostMapping("/{customerName}/addItem")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity addItemTocart(@RequestBody Item item,@PathVariable(name="customerName") String customerName) throws CartNotFoundException, UserNameNotFoundException {


        Cart cart = cartService.getCartOfCustomer(customerName);
        item.setCart(cart);
        itemService.addItem(item);
        return new ResponseEntity(item,HttpStatus.CREATED);
    }

    @GetMapping(value = "/{customerName}/getCart",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity getCartOfCustomer(@PathVariable(name="customerName") String customerName) throws UserNameNotFoundException {

        Cart cart = cartService.getCartOfCustomer(customerName);
        return  new ResponseEntity(cart,HttpStatus.OK);
    }

    @PostMapping("/createCart")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity createCart(@RequestBody Cart cart) {

        cartService.createCart(cart);
        return new ResponseEntity(cart,HttpStatus.CREATED);
    }



}
