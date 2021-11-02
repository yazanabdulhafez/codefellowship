package ASAC.codefellowship.Controllers;


import ASAC.codefellowship.Models.AppUser;
import ASAC.codefellowship.Models.Post;
import ASAC.codefellowship.Repositories.AppUserRepository;
import ASAC.codefellowship.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppUserController {


    @Autowired
    PasswordEncoder  encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }


    @PostMapping("/signup")
    public String signUpUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam  String dateOfBirth,
                             @RequestParam String bio){
       AppUser user= new AppUser(username,encoder.encode(password),
               firstName,lastName, Date.valueOf(dateOfBirth),bio);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
       appUserRepository.save(user);

        return "profile";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/myprofile")
public String getMyProfile(Principal principal, Model model){
       AppUser appUser= appUserRepository.findByUsername(principal.getName());
        model.addAttribute("users",appUser);
        List<Post> posts=postRepository.findAllByAppUser(appUser);
        model.addAttribute("posts",posts);
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

    @PostMapping("/users/follow")
    public RedirectView addFollower(int followedUser, Principal p) {
        AppUser primaryUser = appUserRepository.findByUsername(p.getName());
        primaryUser.addFollower(appUserRepository.findById(followedUser).get());
        appUserRepository.save(primaryUser);
        return new RedirectView("/users");
    }

    @PostMapping("/users/unfollow")
    public RedirectView removeFollower(int unfollowedUser, Principal p) {
        AppUser primaryUser = appUserRepository.findByUsername(p.getName());
        primaryUser.removeFollower(appUserRepository.findById(unfollowedUser).get());
        appUserRepository.save(primaryUser);
        return new RedirectView("/users");
    }

}
