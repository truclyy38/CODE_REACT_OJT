package ojt.demo.Controller;

import ojt.demo.Entity.Semester;
import ojt.demo.Repository.SemesterRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/semester")
public class SemesterController {

    @Autowired
    private SemesterRepository semesterRepository;

    //    get
    @GetMapping("")
    public List<Semester> getAllTeacher() {
        return semesterRepository.findAll();
    }


    //    add
    @PostMapping("")
    public Semester createSemester(@RequestBody Semester semester) {
        return semesterRepository.save(semester);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable Integer id) {

        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(semester);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Semester> updateSemester(@PathVariable Integer id, @RequestBody Semester semesterUpdate) {
        Semester semester = semesterRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Semester not found exist at id:" + id));

        semester.setId(semesterUpdate.getId());
        semester.setClassId(semesterUpdate.getClassId());
        semester.setCourseId(semesterUpdate.getCourseId());
        semester.setSemester(semesterUpdate.getSemester());


        Semester updateSemester = semesterRepository.save(semester);
        return ResponseEntity.ok(updateSemester);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSemesters(@PathVariable Integer id) {
        Semester semester = semesterRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Semester not found exist at id:" + id));
        semesterRepository.delete(semester);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
