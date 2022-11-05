package com.thienlinh.vegetable.controllers;

import com.thienlinh.vegetable.model.Order;
import com.thienlinh.vegetable.model.User;
import com.thienlinh.vegetable.service.ItemService;
import com.thienlinh.vegetable.service.OrderService;
import com.thienlinh.vegetable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
@SuppressWarnings({"java:S1905"})
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    String logged = "logged";

    @RequestMapping(value = "/orders")
    public String getOrdersForUser(Model model, HttpSession session){

        Order order=new Order();
        model.addAttribute("order",order);

        model.addAttribute(logged,(User)session.getAttribute(logged));
        model.addAttribute("user",(User)session.getAttribute("user"));
        return "order";
    }
    @PostMapping(value = "/orders/submit")
    public String submitOrder(Order order, RedirectAttributes attributes, HttpSession session){
        if (session.getAttribute("cart") == null) {
            return "redirect:/";
        } else if ((order != null)) {
            User newUser=(User)session.getAttribute("user");
            User existingUser=(User) session.getAttribute(logged);
            order.setItemList((List)session.getAttribute("cart"));
            order.setDate(LocalDate.now());
            order.setTotalPrice((int)session.getAttribute("price"));

            if ((newUser == null)) {
                order.setUser(existingUser);
            } else {
                order.setUser(newUser);
            }

            orderService.addOrder(order);
            itemService.updateItemQuantity(order.getItemList());
            ((List) session.getAttribute("cart")).clear();
            session.removeAttribute("cart");
            session.removeAttribute("price");
        } else {
            attributes.addFlashAttribute("orderFailedMessage", "Something went wrong");
            return "redirect:cart";
        }
        return "redirect:/";
    }
}
