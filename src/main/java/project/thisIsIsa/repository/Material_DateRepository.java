package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import project.thisIsIsa.model.Material;
import project.thisIsIsa.model.Material_Date;

import java.util.Set;

public interface Material_DateRepository extends CrudRepository<Material_Date,Integer> {

    @Query(value = "SELECT * FROM material_date WHERE date_id = :dateId", nativeQuery = true)
    Set<Material_Date> findMaterial_DateByDateId(@Param("dateId") Integer dateId);

    @Query(value = "SELECT * FROM material_date WHERE material_id = :materialId", nativeQuery = true)
    Set<Material_Date> findMaterial_DateByMaterialId(@Param("materialId") Integer materialId);

}
