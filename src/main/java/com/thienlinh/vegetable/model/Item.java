package com.thienlinh.vegetable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name="Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private int quantity;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="catId")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "itemsinorder",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
    private List<Order> orderList;

    public Item(int id,String name, double price, int quantity) {
         this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
