package ojt.demo.Controller;

import ojt.demo.Entity.Role;
import ojt.demo.Repository.RoleRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    //    get
    @GetMapping("")
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    //    add
    @PostMapping("")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(role);
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role roleUpdate) {
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Role not found exist at id:" + id));

        role.setId(roleUpdate.getId());
        role.setName(roleUpdate.getName());

        Role updateRole = roleRepository.save(role);
        return ResponseEntity.ok(updateRole);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRoles(@PathVariable Integer id) {
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Role not found exist at id:" + id));
        roleRepository.delete(role);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
