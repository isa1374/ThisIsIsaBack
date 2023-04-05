package project.thisIsIsa.model;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DynamicInsert
@DynamicUpdate
public class Game_Artifact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @Column(name = "created_by", nullable = false)
    private Integer created_by;

    @Column(name = "modified_by", nullable = false)
    private Integer modified_by;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;
     
    @ManyToOne
    @JoinColumn(name = "game_id")
    Game game_id;

    @ManyToOne
    @JoinColumn(name = "artifact_id")
    Artifact artifact_id;

    public Game_Artifact() {

    }
    
    public Game_Artifact(Integer id, Boolean completed, Integer created_by, Integer modified_by, Date created, Date modified,
            Game game_id, Artifact artifact_id) {
        this.id = id;
        this.completed = completed;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.created = created;
        this.modified = modified;
        this.game_id = game_id;
        this.artifact_id = artifact_id;
    }
    
    // Get value of properties

    public Integer getId() {
        return id;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Integer getCreatedBy() {
        return created_by;
    }

    public Integer getModifiedBy() {
        return modified_by;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public Game getGame() {
        return game_id;
    }

    public Artifact getArtifact() {
        return artifact_id;
    }

    // Set values of properties 

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setCreatedBy(Integer created_by) {
        this.created_by = created_by;
    }

    public void setModifiedBy(Integer modified_by) {
        this.modified_by = modified_by;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setGame(Game game_id) {
        this.game_id = game_id;
    }

    public void setArtifact(Artifact artifact_id) {
        this.artifact_id = artifact_id;
    }

}
