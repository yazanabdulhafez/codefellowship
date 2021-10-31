package ASAC.codefellowship.Repositories;

import ASAC.codefellowship.Models.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser,Integer> {
    AppUser findByUsername(String username);
}
