package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Game;

import java.util.Set;

public interface GameRepository extends CrudRepository<Game,Integer>{

    @Query(value = ("SELECT * FROM game WHERE name LIKE %" + ":name"+ "%"), nativeQuery = true)
    Set<Game> findGameByName(@Param("name") String name);
}
