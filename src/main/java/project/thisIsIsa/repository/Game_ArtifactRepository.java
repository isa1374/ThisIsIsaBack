package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Game_Artifact;

import java.util.Set;

public interface Game_ArtifactRepository extends CrudRepository<Game_Artifact,Integer> {
    @Query(value = "SELECT * FROM game_artifact WHERE completed = 1", nativeQuery = true)
    Set<Game_Artifact> getAllCompleted();

    @Query(value = "SELECT * FROM game_artifact WHERE completed = 0", nativeQuery = true)
    Set<Game_Artifact> getAllIncomplete();

    @Query(value = "SELECT * FROM game_artifact WHERE artifact_id = :artifactId", nativeQuery = true)
    Set<Game_Artifact> findGame_ArtifactByArtifactId(@Param("artifactId") Integer artifactId);

    @Query(value = "SELECT * FROM game_artifact WHERE game_id = :gameId", nativeQuery = true)
    Set<Game_Artifact> findGame_ArtifactByGameId(@Param("gameId") Integer gameId);

}
