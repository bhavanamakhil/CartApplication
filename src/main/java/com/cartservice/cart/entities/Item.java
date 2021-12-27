package com.cartservice.cart.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.net.Inet4Address;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    private String itemName;

    @Column(length = 500)
    private String itemDescription;

    @Column(nullable = false)
    private String category;

    private double itemCost;

    private LocalDateTime mfgDate;

    private int unitsLeft;


    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId && Double.compare(item.itemCost, itemCost) == 0 && unitsLeft == item.unitsLeft && Objects.equals(itemName, item.itemName) && Objects.equals(itemDescription, item.itemDescription) && Objects.equals(category, item.category) && Objects.equals(mfgDate, item.mfgDate) && Objects.equals(cart, item.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemDescription, category, itemCost, mfgDate, unitsLeft, cart);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", category='" + category + '\'' +
                ", itemCost=" + itemCost +
                ", mfgDate=" + mfgDate +
                ", unitsLeft=" + unitsLeft +
                ", cart=" + cart +
                '}';
    }

    public int getUnitsLeft() {
        return unitsLeft;
    }

    public void setUnitsLeft(int unitsLeft) {
        this.unitsLeft = unitsLeft;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public LocalDateTime getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDateTime mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
