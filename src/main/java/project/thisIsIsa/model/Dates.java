package project.thisIsIsa.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@DynamicInsert
@DynamicUpdate
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "created_by", nullable = false)
    private Integer created_by;

    @Column(name = "modified_by", nullable = false)
    private Integer modified_by;
    
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;

    @OneToMany(mappedBy = "date_id")
    Set<Artifact_Date> artifactdates;
    
    @OneToMany(mappedBy = "date_id")
    Set<Material_Date> materialdates;

    public Dates() {

    }
    
    public Dates(Integer id, String month, Integer created_by, Integer modified_by, Date created, Date modified) {
        this.id = id;
        this.month = month;
        this.created_by = created_by;
        this.modified_by = modified_by;
        this.created = created;
        this.modified = modified;
    }
    
    //Get values of properties 

    public Integer getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public Integer created_by() {
        return created_by;
    }

    public Integer modified_by() {
        return modified_by;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    //Set values for properties

    public void setMonth(String month) {
        this.month = month;
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

}
