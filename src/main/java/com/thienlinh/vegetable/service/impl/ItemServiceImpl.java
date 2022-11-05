package com.thienlinh.vegetable.service.impl;

import com.thienlinh.vegetable.domain.ItemRepository;
import com.thienlinh.vegetable.model.Item;
import com.thienlinh.vegetable.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> listOfItems() {
        return itemRepository.listOfItems();
    }

    @Override
    public void addItem(Item item) {
        itemRepository.addItem(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.deleteItem(item);
    }

    @Override
    public Item findItemById(int id) {
        return itemRepository.findById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }

    public void updateItemQuantity(List<Item> items)
    {
        itemRepository.updateItemQuantity(items);
    }
}
