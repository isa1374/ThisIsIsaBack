package project.thisIsIsa.repository;

import org.springframework.data.repository.CrudRepository;

import project.thisIsIsa.model.Material;

public interface MaterialRepository extends CrudRepository<Material,Integer> {
    
}
