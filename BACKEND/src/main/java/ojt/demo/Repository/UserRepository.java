package ojt.demo.Repository;

import ojt.demo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ojt.demo.Entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.roleId = :roleid")
    List<User> findUserByRoleId(@Param("roleid") Integer roleid);

    @Query("SELECT user FROM User user WHERE user.name = :user_name")
    List<User> findUserByName(@Param("user_name") String user_name);

    User findByName(String name);

    List<User> findByNameContaining(String name);

    boolean existsByPhone(String phone);
    boolean existsById(int id);
}
