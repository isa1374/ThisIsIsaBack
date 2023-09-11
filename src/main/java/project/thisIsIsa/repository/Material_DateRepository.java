package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Material;
import project.thisIsIsa.model.Material_Date;

import java.util.Set;

public interface Material_DateRepository extends CrudRepository<Material_Date,Integer> {

    @Query(value = "SELECT * FROM material WHERE id = (SELECT material_id FROM material_date LEFT JOIN dates ON month = :month)", nativeQuery = true)
    Set<Material> findMaterialByMonth(@Param("month") String month);

}
