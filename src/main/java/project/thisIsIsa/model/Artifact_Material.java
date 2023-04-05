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
public class Artifact_Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "needed_quantity", nullable = false)
    private Integer needed_quantity;

    @Column(name = "current_quantity", nullable = false)
    private Integer current_quantity;

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
    @JoinColumn(name = "artifact_id")
    Artifact artifact_id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    Material material_id;
    
    public Artifact_Material() {

    }
    
    public Artifact_Material(Integer id, Integer needed_quantity, Integer current_quantity, Boolean completed, Integer created_by,
            Integer modified_by, Date created, Date modified, Artifact artifact_id, Material material_id) {
        this.id = id;
        this.needed_quantity = needed_quantity;
        this.current_quantity = current_quantity;
        this.completed = completed;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.created = created;
        this.modified = modified;
        this.artifact_id = artifact_id;
        this.material_id = material_id;
    }
    
    // Get value of properties 

    public Integer getId() {
        return id;
    }

    public Integer getNeededQuantity() {
        return needed_quantity;
    }

    public Integer getCurrentQuantitu() {
        return current_quantity;
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

    public Artifact getArtifact() {
        return artifact_id;
    }

    public Material getMaterial() {
        return material_id;
    }

    // Set values of properties 

    public void setNeededQuantity(Integer needed_quantity) {
        this.needed_quantity = needed_quantity;
    }

    public void setCurrentQuantity(Integer current_quantity) {
        this.current_quantity = current_quantity;
    }

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

    public void setArtifact(Artifact artifact_id) {
        this.artifact_id = artifact_id;
    }

    public void setMaterial(Material material_id) {
        this.material_id = material_id;
    }
}
