package com.thienlinh.vegetable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @NotNull
    private String name;
    @Id
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String mobileNumber;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Order> orderList;

    public User(String name, String email, String password, String mobileNumber, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public void add(Order order){

        if(orderList == null){
            List<Item> orderList = new ArrayList<Item>();
        }
        orderList.add(order);

    }
}
