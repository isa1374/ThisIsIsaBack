package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Material;

import java.util.Set;

public interface MaterialRepository extends CrudRepository<Material,Integer> {

    @Query(value = ("SELECT * FROM material WHERE name LIKE %"+":name"+"%"), nativeQuery = true)
    Set<Material> findMaterialByName(@Param("name") String name);

}
