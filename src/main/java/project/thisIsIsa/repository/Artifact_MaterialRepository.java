package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Artifact_Material;

import java.util.Set;

public interface Artifact_MaterialRepository extends CrudRepository<Artifact_Material,Integer>{
    @Query(value = "SELECT * FROM artifact_material WHERE game_id = :gameId", nativeQuery = true)
    Set<Artifact_Material> findArtifact_MaterialByGameId(@Param("gameId") Integer gameId);

    @Query(value = "SELECT * FROM artifact_material WHERE material_id = :materialId", nativeQuery = true)
    Set<Artifact_Material> findArtifact_MaterialByMaterialId(@Param("materialId") Integer material_id);

    @Query(value = "SELECT * FROM artifact_material WHERE needed_quantity = :neededQuantity", nativeQuery = true)
    Set<Artifact_Material> findArtifact_MaterialByNeededQuantity(@Param("neededQuantity") Integer neededQuantity);

    @Query(value = "SELECT * FROM artifact_material WHERE completed = 1", nativeQuery = true)
    Set<Artifact_Material> getAllCompleted();

    @Query(value = "SELECT * FROM artifact_material WHERE completed = 0", nativeQuery = true)
    Set<Artifact_Material> getAllIncomplete();

}
