package ASAC.codefellowship.Controllers;


import ASAC.codefellowship.Models.AppUser;
import ASAC.codefellowship.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class AppUserController {


    @Autowired
    PasswordEncoder  encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

//    @PostMapping("/signup")  //i must add the other things (first name ,lastname,..)
//    public String signUpUser(@RequestParam String username, @RequestParam String password){
//        AppUser appUser = new AppUser(username, encoder.encode(password));
//        appUserRepository.save(appUser);
//        return "login";
//    }
    @PostMapping("/signup")
    public String signUpUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam  String dateOfBirth,
                             @RequestParam String bio){
       AppUser user= new AppUser(username,encoder.encode(password),
               firstName,lastName, Date.valueOf(dateOfBirth),bio);
        try{
            appUserRepository.save(user);
        }catch (Exception exception){
            return "error";
        }
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/myprofile")
public String getMyProfile(Principal principal, Model model){
       AppUser appUser= appUserRepository.findByUsername(principal.getName());
        model.addAttribute("users",appUser);
        return "profile";

    }

@GetMapping("/users")
public String getAllUsers(Principal principal, Model model) {
    AppUser appUser = appUserRepository.findByUsername(principal.getName());
    List<AppUser> allUsers = (List<AppUser>) appUserRepository.findAll();
    model.addAttribute("appUser", appUser);
    model.addAttribute("users", allUsers);
    return "allusers";
}


    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable int id,Principal principal,Model model){
AppUser targetUser=appUserRepository.findById(id).get();
model.addAttribute("targetUser",targetUser);
AppUser currentUser=appUserRepository.findByUsername(principal.getName());
model.addAttribute("currentUser",currentUser);

return "userprofile";
    }

}
