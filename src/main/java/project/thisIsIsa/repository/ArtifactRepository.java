package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Artifact;

import java.util.Set;

public interface ArtifactRepository extends CrudRepository<Artifact,Integer> {
    @Query(value = ("SELECT * FROM artifact WHERE name LIKE" +'%'+":name"+'%'), nativeQuery = true)
    Set<Artifact> findArtifactByName(@Param("name") String name);

}
