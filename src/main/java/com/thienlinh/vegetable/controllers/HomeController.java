package com.thienlinh.vegetable.controllers;

import com.thienlinh.vegetable.model.Item;
import com.thienlinh.vegetable.model.User;
import com.thienlinh.vegetable.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings({"java:S1905", "java:S3740"})
public class HomeController {

    @Autowired
    private ItemService itemService;

    List<Item> cart = new ArrayList<>();

    String logged = "logged";

    String admin = "redirect:/admin";

    @RequestMapping("/")
    public String index(Model model, HttpSession session){
        
        model.addAttribute("listOfItems",itemService.listOfItems());

        User user = (User) session.getAttribute("user");
        User userLogged = (User) session.getAttribute(logged);
        model.addAttribute("user", user);
        model.addAttribute(logged, userLogged);

        return "index";
    }

    @RequestMapping(value="/viewCart/{id}")
    public String addtoCart(@PathVariable int id, HttpSession session, Model model, RedirectAttributes redirectAttributes){
        int totalPrice=0;
        Item item=itemService.findItemById(id);

        if(item.getQuantity() > 0) {
            cart.add(item);
        } else {
            redirectAttributes.addFlashAttribute("itemOutOfStock","Item is out of Stock");
        }

        for (Item sample : cart) {
            totalPrice += sample.getPrice();
        }

        session.setAttribute("cart", cart);
        session.setAttribute("price", totalPrice);
        model.addAttribute(logged, (User) session.getAttribute(logged));
        model.addAttribute("user", (User) session.getAttribute("user"));
        return "cart";
    }
    @RequestMapping(value = "/removeItem/{id}")
    public String removeItem(@PathVariable int id,HttpSession session){
        int totalPrice=0;
        Item remove = new Item();

        for (Item item : cart) {
            int idItem = item.getId();
            if(idItem == id){
                remove = item;
                break;
            }
        }
        cart = (List<Item>) session.getAttribute("cart");
        cart.remove(remove);
        for (Item sample : cart) {
            totalPrice += sample.getPrice();
        }
        session.setAttribute("cart", cart);
        session.setAttribute("price", totalPrice);

        return "cart";
    }

    @RequestMapping(value = "/viewCart")
    public String viewCart(Model model,HttpSession session){
        model.addAttribute(logged,(User)session.getAttribute(logged));
        model.addAttribute("user",(User)session.getAttribute("user"));
        return "cart";
    }
    
    @RequestMapping("/newItem")
    public String newItem(Map map){
        Item item = new Item();
        map.put("newItem",item);
        return "newItem";
    }

    @PostMapping(value = "/addItem")
    public String addItem(Item item, RedirectAttributes redirectAttributes){
        itemService.addItem(item);
        redirectAttributes.addFlashAttribute("addSuccess","Items added successfully");
        return admin;
    }

    @RequestMapping("/admin")
    public String showItemsToAdmin(Map map){
        map.put("listOfItems",itemService.listOfItems());
        return "admin";
    }

    @RequestMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id,RedirectAttributes redirectAttributes){

        Item item = itemService.findItemById(id);
        itemService.deleteItem(item);
        redirectAttributes.addFlashAttribute("deleteSuccess","Deleted item with id: "+id);
        return admin;
    }

    @RequestMapping("/updatePage/{id}")
    public String updatePage(Map map,@PathVariable int id){
        map.put("itemToBeUpdated",itemService.findItemById(id));
        return "updateItem";
    }

    @PostMapping(value = "/updateItem")
    public String updateItem(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("price")double price,@RequestParam("quantity")int quantity, RedirectAttributes redirectAttributes){

        // Find item by id
        Item item = itemService.findItemById(id);

        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);

        // Update item
        itemService.updateItem(item);
        redirectAttributes.addFlashAttribute("updateSuccess","Updated item with id: "+id);
        return admin;
    }
}
