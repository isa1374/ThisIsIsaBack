package project.thisIsIsa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.thisIsIsa.model.Users;

import java.util.Set;

public interface UserRepository extends CrudRepository<Users,Integer> {

    @Query(value = ("SELECT * FROM users WHERE name LIKE" +'%'+":name"+'%'), nativeQuery = true)
    Set<Users> findUsersByName(@Param("name") String name);

    @Query(value = ("SELECT * FROM users WHERE email LIKE" +'%'+":email"+'%'), nativeQuery = true)
    Set<Users> findUsersByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE admin = 1", nativeQuery = true)
    Set<Users> getListOfAdmins();

    @Query(value = "SELECT * FROM users WHERE active = 1", nativeQuery = true)
    Set<Users> getListOfActiveUsers();

    @Query(value = "SELECT * FROM users WHERE active = 0", nativeQuery = true)
    Set<Users> getListOfInactiveUsers();

}
