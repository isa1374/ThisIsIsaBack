package project.thisIsIsa.repository;

import org.springframework.data.repository.CrudRepository;
import project.thisIsIsa.model.Game;

public interface GameRepository extends CrudRepository<Game,Integer>{
    
}
