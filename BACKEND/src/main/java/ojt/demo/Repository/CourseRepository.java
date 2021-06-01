package ojt.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ojt.demo.Entity.Course;
import java.util.List;



@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

        @Query("SELECT c FROM Course c WHERE c.majorid = :majorid")
        List<Course> findCoursesByMajorid(@Param("majorid") Integer majorid) ;

        @Query("SELECT c FROM Course c WHERE c.semester = :semester")
        List<Course> findCoursesBySemester(@Param("semester") Integer semester) ;

}
