package com.cartservice.cart;

import com.cartservice.cart.entities.Cart;
import com.cartservice.cart.entities.Item;
import com.cartservice.cart.services.CartNotFoundException;
import com.cartservice.cart.services.CartService;
import com.cartservice.cart.services.ItemService;
import com.cartservice.cart.services.impl.ItemServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CartApplication {

	public static void main(String[] args) throws CartNotFoundException {
		ApplicationContext ctx = SpringApplication.run(CartApplication.class, args);

		ItemService itemService = ctx.getBean(ItemService.class);
		CartService cartService = ctx.getBean(CartService.class);

		Item item = new Item();

		Cart cart = new Cart();
		cart.setCustomerName("Akhil3");
		cartService.createCart(cart);

		item.setCart(cart);
		item.setItemCost(2500);
		item.setItemDescription("Amazon echo dot comes with voice assistant Alexa");
		item.setItemName("Echo dot");
		item.setCategory("Electronics");
		item.setMfgDate(LocalDateTime.now());
		itemService.addItem(item);

        Map<Integer,Item> map = new HashMap<Integer,Item>();
        map.put(121,item);
        cart.setUnitsOfItem(map);
        cartService.updateCart(cart);

	}

}
