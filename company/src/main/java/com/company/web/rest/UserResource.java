package com.company.web.rest;

import com.company.entity.Authority;
import com.company.entity.User;
import com.company.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){

        String role = user.getAuthorities().stream().map(Authority::getPositionName).collect(Collectors.joining());

        if (role.equals("ROLE_EMPLOYEE")){
            if (user.getEmployeeManagement() == null){
                return ResponseEntity.badRequest().build();
            }
        }

        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/register/update")
    public ResponseEntity update(@RequestBody User user){
        if (user.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/register/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/register")
    public ResponseEntity getAll(){
        List<User> result = userService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/register/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("User was deleted successfully!");
    }
}
