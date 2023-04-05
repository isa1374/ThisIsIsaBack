package project.thisIsIsa.repository;

import org.springframework.data.repository.CrudRepository;

import project.thisIsIsa.model.Artifact;

public interface ArtifactRepository extends CrudRepository<Artifact,Integer> {
    
}
