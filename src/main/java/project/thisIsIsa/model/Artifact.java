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
public class Artifact {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        @Column(name = "name", nullable = false)
        private String name;
        @Column(name = "description", nullable = true )
        private String description;
        @Column(name = "created_by", nullable = false)
        private Integer created_by;
        @Column(name = "modified_by", nullable = false)
        private Integer modified_by;
        @Column(name = "created", nullable = false)
        private Date created;
        @Column(name = "modified", nullable = false)
        private Date modified;

        @OneToMany(mappedBy = "artifact_id")
        Set<Game_Artifact> gameArtifacts;

        @OneToMany(mappedBy = "artifact_id")
        Set<Artifact_Material> artifactmaterials; 

        @OneToMany(mappedBy = "artifact_id")
        Set<Artifact_Date> artifactdates;
        
        public Artifact() {

        }
        
        public Artifact(Integer id, String name, String description, Integer created_by, Integer modified_by, Date created,
                Date modified) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.created_by = created_by;
            this.modified_by = modified_by;
            this.created = created;
            this.modified = modified;
        }
        
        
        //Get values of properties

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
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

        //Set value for properties

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
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
