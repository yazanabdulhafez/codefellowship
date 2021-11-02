package ASAC.codefellowship.Repositories;


import ASAC.codefellowship.Models.AppUser;
import ASAC.codefellowship.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post,Integer> {
     List<Post> findAllByAppUser(AppUser appUser);
}
