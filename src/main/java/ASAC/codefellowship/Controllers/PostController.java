package ASAC.codefellowship.Controllers;


import ASAC.codefellowship.Models.AppUser;
import ASAC.codefellowship.Models.Post;
import ASAC.codefellowship.Repositories.AppUserRepository;
import ASAC.codefellowship.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PostRepository postRepository;


    //get the posts for the Authenticated  user
    @GetMapping("/post")
    public String getPost(Principal p, Model m) {
        AppUser appUser = appUserRepository.findByUsername(p.getName());
        List<Post> userPosts=postRepository.findAllByAppUser(appUser);
        m.addAttribute("posts", userPosts);
        return "profile";
    }

//create a post for the Authenticated user
    @PostMapping("/post")
    public RedirectView createPost(String body, Principal p) {
        AppUser appUser = appUserRepository.findByUsername(p.getName());
        Post post = new Post(body, appUser);
        postRepository.save(post);
        return new RedirectView("/post");
    }

    //get the posts for the followed users
    @GetMapping("/feed")
    public String getFeed(Principal p, Model m) {
        AppUser appUser = appUserRepository.findByUsername(p.getName());
        m.addAttribute("appUser", appUser);
        return "feed";
    }
}
