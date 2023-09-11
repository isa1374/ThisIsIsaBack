package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Artifact;
import project.thisIsIsa.model.Artifact_Date;

import java.util.Set;

public interface Artifact_DateRepository extends CrudRepository<Artifact_Date,Integer> {
    @Query(value = "SELECT  * FROM artifact WHERE id = (SELECT artifact_id FROM artifact_date LEFT JOIN dates ON month = :month)", nativeQuery = true)
    Set<Artifact> findArtifactByMonth (@Param("month") String month);

}
