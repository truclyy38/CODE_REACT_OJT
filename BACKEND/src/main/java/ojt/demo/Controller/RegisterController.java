package ojt.demo.Controller;

import ojt.demo.Entity.Register;
import ojt.demo.Repository.RegisterRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;

    //    get
    @GetMapping("")
    public List<Register> getAllRegister() {
        return registerRepository.findAll();
    }


    //    add
    @PostMapping("")
    public Register createRegister(@RequestBody Register register) {
        return registerRepository.save(register);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Register> getRegisterById(@PathVariable Integer id) {

        Register register = registerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(register);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable Integer id, @RequestBody Register registerUpdate) {
        Register register = registerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Register not found exist at id:" + id));

        register.setId(registerUpdate.getId());
        register.setClassid(registerUpdate.getClassid());
        register.setUserid(registerUpdate.getUserid());

        Register updateRegister = registerRepository.save(register);
        return ResponseEntity.ok(updateRegister);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRegisters(@PathVariable Integer id) {
        Register register = registerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Register not found exist at id:" + id));
        registerRepository.delete(register);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
