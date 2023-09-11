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
public class Artifact_Date {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_by", nullable = false)
    private Integer created_by;
    @Column(name = "modified_by", nullable = false)
    private Integer modified_by;
    @Column(name = "created", nullable = false)
    private Date created;
    @Column(name = "modified", nullable = false)
    private Date modified;

    @ManyToOne
    @JoinColumn(name = "artifact_id", nullable = false)
    Artifact artifact_id;

    @ManyToOne 
    @JoinColumn(name = "date_id", nullable = false)
    Dates date_id;

    public Artifact_Date() {

    }
    
    public Artifact_Date(Integer id, Integer created_by, Integer modified_by, Date created, Date modified, Artifact artifact_id,
            Dates date_id) {
        this.id = id;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.created = created;
        this.modified = modified;
        this.artifact_id = artifact_id;
        this.date_id = date_id;
    }
    
    // Get values of properties 

    public Integer getId() {
        return id;
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

    public Artifact getArtifact() {
        return artifact_id;
    }

    public Dates getDate() {
        return date_id;
    }

    // Set values of properties

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

    public void setArtifact(Artifact artifact_id) {
        this.artifact_id = artifact_id;
    }

    public void setDate(Dates date_id) {
        this.date_id = date_id;
    }
}
