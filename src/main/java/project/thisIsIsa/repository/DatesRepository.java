package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Dates;

import java.util.Set;

public interface DatesRepository extends CrudRepository<Dates,Integer> {
    @Query(value = ("SELECT * FROM dates WHERE month LIKE %"+":month"+"%"), nativeQuery = true)
    Set<Dates> findDatesByMonth(@Param("month") String month);
}
