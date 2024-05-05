package com.company.web.rest;

import com.company.entity.Authority;
import com.company.entity.User;
import com.company.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){

        logger.debug("'/register' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {

            if (userService.existsByUsername(user.getUsername())){
                return new ResponseEntity("Please enter other username! Username exists in database!", HttpStatus.BAD_REQUEST);
            }

            if (checkPasswordLength(user.getPassword())){
                return new ResponseEntity("Password length is less than 4", HttpStatus.BAD_REQUEST);
            }

            String role = user.getAuthorities().stream().map(Authority::getPositionName).collect(Collectors.joining());

            if (role.equals("ROLE_EMPLOYEE")){
                if (user.getEmployeeManagement() == null){
                    return ResponseEntity.badRequest().build();
                }
            }

            User result = userService.save(user);
            logger.info(result.getId() + "-id-li user 'company_user' jadvaliga qoshildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(user.getId() + "-id-li user 'company_user' jadvaliga qoshilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/register/update")
    public ResponseEntity update(@RequestBody User user){

        logger.debug("'/register/update' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Put' sorovi qabul qilindi");
        logger.debug("'update' method-i ishga tushdi");

        try {
            if (user.getId() == null){
                return ResponseEntity.badRequest().build();
            }

            User result = userService.save(user);
            logger.info(user.getId() + "-id-agi user malumotlari 'company_user' jadvalida yangilandi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(user.getId() + "-id-agi user malumotlari 'company_user' jadvalida yangilanmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/register/{id}")
    public ResponseEntity getById(@PathVariable Long id){

        logger.debug("'/register/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getById' method-i ishga tushdi");

        try {
            User user = userService.findById(id);
            logger.info(id + "-id-li user malumotlari 'company_user' jadvalidan qabul qilindi");
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error(id + "-id-li user malumotlari 'company_user' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/register")
    public ResponseEntity getAll(){

        logger.debug("'/register' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<User> result = userService.findAll();
            logger.info("List<User> tipidagi ma'lumot 'company_user' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<User> tipidagi ma'lumot 'company_user' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/register/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        logger.debug("'/register/{id}'  endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            userService.deleteById(id);
            logger.info(id + "-id-li user malumotlari 'company_user' jadvalidan ochirildi");
            return ResponseEntity.ok("User was deleted successfully!");
        } catch (Exception e) {
            logger.error(id + "-id-li user malumotlari 'company_user' jadvalidan ochirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean checkPasswordLength(String password) {
        return password.length() <= 4;
    }
}
