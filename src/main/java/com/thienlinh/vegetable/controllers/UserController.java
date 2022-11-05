package com.thienlinh.vegetable.controllers;

import com.thienlinh.vegetable.model.User;
import com.thienlinh.vegetable.service.ItemService;
import com.thienlinh.vegetable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SuppressWarnings({"java:S3740"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    String userProfile = "userProfile";

    String redirect = "redirect:/";

    String login = "login";
    String sessionUser = "sessionuser";

    @RequestMapping("/signup")
    public String newUser(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userSignup", user);
        return "signup";
    }


    @PostMapping(value = "/adduser")
    public String addUser(User user, RedirectAttributes redirectAttributes, HttpSession hs)
    {
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("signedUp","Signup Successful");
        hs.setAttribute("user", user);
        return redirect;
    }

    @RequestMapping("/login")
    public String loginUser(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userLogin", user);
        return login;
    }

    @PostMapping(value ="/userlogin")
    public String checkUser(User user,Model model,HttpSession hs,Map map)
    {
        User userLogin = userService.findByEmail(user.getEmail());
        if (userLogin != null) {
            if (userLogin.getPassword().equals(user.getPassword())) {
                model.addAttribute("userLogin","for login");
                model.addAttribute("loggedIn", "Login Successful");
                model.addAttribute("listOfItems",itemService.listOfItems());
                hs.setAttribute(sessionUser, userLogin);
                return "index";
            }
            model.addAttribute("passwordcheck","Invalid Password.Please try again..");
            return login;
        }
        model.addAttribute("emailcheck","Sorry,we don't recognise this email address");
        return login;

    }

    @PostMapping(value = "/profile")
    public String userProfile(@RequestParam("email") String mail, Model model,HttpSession hs)
    {
        User user = userService.findByEmail(mail);
        model.addAttribute("userdetails",user);
        model.addAttribute(userProfile,"for profile");

        return userProfile;
    }

    @PostMapping(value = "/deleteaccount")
    public String deleteUser(@RequestParam("email") String mail,Model model)
    {
        User user=userService.findByEmail(mail);
        if(user!=null)
        {
            userService.deleteUser(user);
            model.addAttribute("deletion","Account deleted successfully");
            return "index";
        }
        return userProfile;
    }
    @RequestMapping(value ="/update")
    public String updateUser(Model model,@RequestParam("email") String mail,HttpSession session)
    {
        User user = userService.findByEmail(mail);
        model.addAttribute(sessionUser, session.getAttribute(sessionUser));
        model.addAttribute("userupdate", user);

        return "updateUser";

    }
    @PostMapping(value ="/updateaccount")
    public String updateAccount(User user,Model model,HttpSession session){
        User userUpdate = userService.findByEmail(user.getEmail());
        if (userUpdate != null)
        {
            userUpdate.setName(user.getName());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setMobileNumber(user.getMobileNumber());
            userUpdate.setAddress(user.getAddress());
            User userUpdateAccount = userService.updateUser(user);
            session.setAttribute("user", userUpdateAccount);
            model.addAttribute("update","successfully");
            return redirect;
        }
        return userProfile;
    }

    @PostMapping(value = "/logout")
    public String logOut(HttpSession hs,Model model)
    {
        model.addAttribute(sessionUser,hs.getAttribute(sessionUser));
        hs.invalidate();
        return redirect;
    }
}
