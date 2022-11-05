package com.thienlinh.vegetable.domain;

import com.thienlinh.vegetable.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> listOfItems();
    void addItem(Item item);
    void deleteItem(Item item);
    Item findById(int id);
    void updateItem(Item item);
    void updateItemQuantity(List<Item> items);
}
