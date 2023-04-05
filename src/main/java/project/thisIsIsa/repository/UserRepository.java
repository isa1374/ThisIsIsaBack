package project.thisIsIsa.repository;

import org.springframework.data.repository.CrudRepository;
import project.thisIsIsa.model.Users;

public interface UserRepository extends CrudRepository<Users,Integer> {
    
}
