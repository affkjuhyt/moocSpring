package com.thienlinh.vegetable.domain.impl;

import com.thienlinh.vegetable.domain.ItemRepository;
import com.thienlinh.vegetable.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Item> listOfItems() {
        Session session =sessionFactory.openSession();
        List<Item> items = session.createQuery("from Items").getResultList();
        session.close();

        return items;
    }

    @Override
    public void addItem(Item item) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteItem(Item item) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public Item findById(int id) {
        Session session =sessionFactory.openSession();
        Item item=session.get(Item.class,id);
        session.close();
        return item;
    }

    @Override
    public void updateItem(Item item) {
    Session session=sessionFactory.openSession();
    session.beginTransaction();
    session.saveOrUpdate(item);
    session.getTransaction().commit();
    session.close();
    }
    @Override
    public void updateItemQuantity(List<Item> items) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        for (Item item:
                items) {
            item.setQuantity(item.getQuantity()-1);
            session.saveOrUpdate(item);
        }
        session.getTransaction().commit();
        session.close();
    }
}
