package com.thienlinh.vegetable.service;

import com.thienlinh.vegetable.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> listOfItems();

    void addItem(Item item);

    void deleteItem(Item item);

    Item findItemById(int id);

    void updateItem(Item item);

    void updateItemQuantity(List<Item> items);
}
