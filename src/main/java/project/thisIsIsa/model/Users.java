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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "active", nullable = false)
    private Boolean active;
    @Column(name = "admin", nullable = false)
    private Boolean admin;
    @Column(name = "modified_by", nullable = false)
    private Integer modified_by;
    @Column(name = "photo", nullable = true)
    private String photo;
    @Column(name = "created", nullable = false)
    private Date created;
    @Column(name = "modified", nullable = false)
    private Date modified;

    @OneToMany(mappedBy = "user_id")
    Set<User_Game> usergames;
    
    public Users() {

    }
    
    public Users(Integer id, String name, String password, String email, Boolean active, Boolean admin, Integer created_by,
            Integer modified_by, String photo, Date modified, Date created) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.active = active;
        this.admin = admin;
        this.modified_by = modified_by;
        this.photo = photo;
        this.modified = modified;
        this.created = created;
    }
    
    //Get values of properties
   
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Integer getModifiedBy() {
        return modified_by;
    }

    public String getPhoto() {
        return photo;
    }

    public Date getModified() {
        return modified;
    }

    // Set values to properties

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setModifiedBy(Integer modified_by) {
        this.modified_by = modified_by;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

}
