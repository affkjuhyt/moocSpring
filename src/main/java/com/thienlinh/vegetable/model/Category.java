package com.thienlinh.vegetable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {

    @Id
    private int catId;

    @NotNull
    private String catName;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Item> itemList;

    public Category(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }
}
