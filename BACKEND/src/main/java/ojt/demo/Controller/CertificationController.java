package ojt.demo.Controller;

import ojt.demo.Entity.Certification;
import ojt.demo.Repository.CertificationRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/certification")
public class CertificationController {

    @Autowired
    private CertificationRepository certificationRepository;

    //    get
    @GetMapping("")
    public List<Certification> getAllTeacher() {
        return certificationRepository.findAll();
    }


    //    add
    @PostMapping("")
    public Certification createCertification(@RequestBody Certification certification) {
        return certificationRepository.save(certification);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable Integer id) {

        Certification certification  = certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(certification);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Certification> updateCertification(@PathVariable Integer id, @RequestBody Certification certificationUpdate) {
        Certification certification = certificationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Certification not found exist at id:" + id));

        certification.setId(certificationUpdate.getId());
        certification.setName(certificationUpdate.getName());

        Certification updateCertification = certificationRepository.save(certification);
        return ResponseEntity.ok(updateCertification);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCertifications(@PathVariable Integer id) {
        Certification certification = certificationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Certification not found exist at id:" + id));
        certificationRepository.delete(certification);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
