package ojt.demo.Controller;

import ojt.demo.Entity.Course;
import ojt.demo.Repository.CourseRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    //    get
    @GetMapping("")
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }


    //    add
    @PostMapping("")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(course);
    }

    @GetMapping("/major/{majorid}")
    public List<Course> getCourseByMajorid(@PathVariable Integer majorid) {

        return courseRepository.findCoursesByMajorid(majorid);
    }

    @GetMapping("/semester/{semester}")
    public List<Course> getCourseBySemester(@PathVariable Integer semester) {

        return courseRepository.findCoursesBySemester(semester);
    }

//    @GetMapping("/major/{majorid}/semester/{semester}")
//    public List<Course> getCourseBySemester(@PathVariable Integer semester, @PathVariable Integer majorid) {
//
//        return courseRepository.findCoursesBySemester(semester, majorid);
//    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course courseUpdate) {
        Course course = courseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Course not found exist at id:" + id));

        course.setId(courseUpdate.getId());
        course.setName(courseUpdate.getName());
        course.setCode(courseUpdate.getCode());
        course.setMajorid(courseUpdate.getMajorid());
        course.setSemester(courseUpdate.getSemester());

        Course updateCourse = courseRepository.save(course);
        return ResponseEntity.ok(updateCourse);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourses(@PathVariable Integer id) {
        Course course = courseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Course not found exist at id:" + id));
        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
