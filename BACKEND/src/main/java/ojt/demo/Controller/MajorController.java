package ojt.demo.Controller;

import ojt.demo.Entity.Major;
import ojt.demo.Repository.MajorRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/major")
public class MajorController {

    @Autowired
    private MajorRepository majorRepository;

    //    get
    @GetMapping("")
    public List<Major> getAllMajor() {
        return majorRepository.findAll();
    }


    //    add
    @PostMapping("")
    public Major createMajor(@RequestBody Major major) {
        return majorRepository.save(major);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Major> getMajorById(@PathVariable Integer id) {

        Major student = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(student);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Major> updateMajor(@PathVariable Integer id, @RequestBody Major majorUpdate) {
        Major major = majorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Major not found exist at id:" + id));

        major.setId(majorUpdate.getId());
        major.setName(majorUpdate.getName());

        Major updateMajor = majorRepository.save(major);
        return ResponseEntity.ok(updateMajor);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMajors(@PathVariable Integer id) {
        Major major = majorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Major not found exist at id:" + id));
        majorRepository.delete(major);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}