package ojt.demo.Controller;

import ojt.demo.Entity.Classes;
import ojt.demo.Repository.ClassesRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/classes")
public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    //    get
    @GetMapping("")
    public List<Classes> getAllTeacher() {
        return classesRepository.findAll();
    }

    //    add
    @PostMapping("")
    public Classes createClasses(@RequestBody Classes classes) {
        return classesRepository.save(classes);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Classes> getClassesById(@PathVariable Integer id) {

        Classes student = classesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(student);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Classes> updateClasses(@PathVariable Integer id, @RequestBody Classes classesUpdate) {
        Classes classes = classesRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Classes not found exist at id:" + id));

        classes.setId(classesUpdate.getId());
        classes.setName(classesUpdate.getName());
        classes.setTotal(classesUpdate.getTotal());
        classes.setSemester(classesUpdate.getSemester());


        Classes updateClasses = classesRepository.save(classes);
        return ResponseEntity.ok(updateClasses);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClassess(@PathVariable Integer id) {
        Classes classes = classesRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Classes not found exist at id:" + id));
        classesRepository.delete(classes);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
