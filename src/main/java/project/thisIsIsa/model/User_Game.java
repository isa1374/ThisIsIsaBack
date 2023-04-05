package project.thisIsIsa.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
public class User_Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    Game game_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user_id;
    
    public User_Game() {

    }
     
    public User_Game(Integer id, Game game_id, Users user_id) {
        this.id = id;
        this.game_id = game_id;
        this.user_id = user_id;
    }

    // Get value of properties

    public Integer getId() {
        return id;
    }

    public Game getGame() {
        return game_id;
    }

    public Users getUser() {
        return user_id;
    }

    // Set value of properties

    public void setGame(Game game_id) {
        this.game_id = game_id;
    }

    public void setUser(Users user_id) {
        this.user_id = user_id; 
    }

    
}
