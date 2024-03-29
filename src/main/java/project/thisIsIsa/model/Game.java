package project.thisIsIsa.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "created", nullable = false)
    private Date created;
    @Column(name = "modified", nullable = false)
    private Date modified;
    @Column(name = "modified_by", nullable = false)
    private Integer modified_by;
    @Column(name = "created_by", nullable = false)
    private Integer created_by;

    @OneToMany(mappedBy = "game_id")
    Set<User_Game> usergames;

    @OneToMany(mappedBy = "game_id")
    Set<Game_Artifact> gameArtifacts;

    public Game() {

    }
    
    public Game(Integer id, String name, String description, Date created, Date modified, Integer created_by, Integer modified_by) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.modified = modified;
        this.created_by = created_by;
        this.modified_by = modified_by;
    }

    //Get values for properties

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }
    
    public Integer getCreatedBy() {
        return created_by;
    }

    public Integer getModifiedBy() {
        return modified_by;
    }

    //Set values for properties

    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setCreatedBy(Integer created_by) {
        this.created_by = created_by;
    }

    public void setModifiedBy(Integer modified_by) {
        this.modified_by = modified_by;
    }

    public String toString(){
        String game = "ID: " + getId().toString() + '\n'+
                      "Name: " + getName() + '\n'+
                      "Description: " + getDescription() + '\n'+
                      "Created: " + getCreated() + '\n'+
                      "Modified: " + getModified() + '\n'+
                      "Created By: " + getCreatedBy() + '\n' +
                      "Modified By: " +  getModifiedBy();
        return game;
    }
}
