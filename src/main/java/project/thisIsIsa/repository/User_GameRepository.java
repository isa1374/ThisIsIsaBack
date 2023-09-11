package project.thisIsIsa.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import project.thisIsIsa.model.User_Game;


public interface User_GameRepository extends CrudRepository<User_Game, Integer> {

    @Query(value = "SELECT * FROM user_game WHERE user_id = :user_id", nativeQuery = true)
    Set<User_Game> findByUserId(@Param("user_id") Integer user_id);

    @Query(value = "SELECT * FROM user_game WHERE game_id = :game_id", nativeQuery = true)
    Set<User_Game> findByGameId(@Param("game_id") Integer game_id);


}
