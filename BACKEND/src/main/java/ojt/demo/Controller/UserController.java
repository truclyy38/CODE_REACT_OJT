package ojt.demo.Controller;

import ojt.demo.Entity.User;
import ojt.demo.Exception.APIRequestException;
import ojt.demo.Repository.UserRepository;
import ojt.demo.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //    get
    @GetMapping("")
    public List<User> getAllTeacher() {
        List<User> users;
        try {
            users = userRepository.findAll();
        } catch (Exception e) {
            throw new APIRequestException("Can not get all the student because the custom exception");
        }
        return users;
    }

    //    add
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult br) {
        long codeUser = 0;
        if (br.hasErrors()) {
            return new ResponseEntity<>(br.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
//        count the same name in DB
        List<User> sameusername = userRepository.findByNameContaining(user.getName());

        if (!sameusername.isEmpty()) {
            codeUser = sameusername.stream().count();
            user.setStudentcode(user.getStudentcode() + codeUser);
            user.setName(user.getName() + codeUser);
            user.setEmail(user.getEmail() + codeUser + "@gmail.com");
            user.setPassword(user.getPassword() + codeUser + "@123");
        }
        else{
            user.setStudentcode(user.getStudentcode() + "0");
            user.setEmail(user.getEmail() + "@gmail.com");
            user.setPassword(user.getPassword() + "@123");
        }

        List<User> users = userRepository.findAll();
        String message = "Add successful";

        if (userRepository.existsByPhone(user.getPhone())) {
            message = "The phone number is existed, please check again!";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //    get By Id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find the student id: " + id));
        return ResponseEntity.ok(user);
    }

    //    get by roleid
    @GetMapping("/role/{roleid}")
    public List<User> getUserByRoleId(@PathVariable Integer roleid) {

        return userRepository.findUserByRoleId(roleid);
    }

    //    get by name
    @GetMapping("/name/{name}")
    public List<User> getUserByName(@PathVariable String name) {

        return userRepository.findUserByName(name);
    }

    //    return sort
    @GetMapping("/sortbynameASC")
    public List<User> sortUserByNameASC() {

        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/sortbynameDSC")
    public List<User> sortUserByNameDSC() {

        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    //    update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User userUpdate) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found exist "));
        String message = "Update Sucessful";
        boolean check = true;
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if ((!id.equals(u.getId())) && (userUpdate.getPhone() == u.getPhone())) {
                message = "There is the same phonenumber of another user";
                check = false;
            }
        }
        if (check) {
            user.setName(userUpdate.getName());
            user.setFullname(userUpdate.getFullname());
            user.setDob(userUpdate.getDob());
            user.setEmail(userUpdate.getEmail());
            user.setGender(userUpdate.getGender());
            user.setCertificationID(userUpdate.getCertificationID());
            user.setMajorid(userUpdate.getMajorid());
            user.setImage(userUpdate.getImage());
            user.setPhone(userUpdate.getPhone());
            user.setPassword(userUpdate.getPassword());
            user.setStudentcode(userUpdate.getStudentcode());

            User updateUser = userRepository.save(user);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    //    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Integer id) {
        String message = "Delete Successful!";
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found exist "));
        boolean check = false;
        if(userRepository.existsById(id)) check= true;
        if (!check) {
            message = "Could not find this user in database, please reload page to delete!";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else userRepository.delete(user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
