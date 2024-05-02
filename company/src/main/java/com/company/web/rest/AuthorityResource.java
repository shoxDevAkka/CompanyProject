package com.company.web.rest;

import com.company.entity.Authority;
import com.company.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final AuthorityService authorityService;

    private final Logger logger = LoggerFactory.getLogger(AuthorityResource.class);

    public AuthorityResource(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/authorities")
    public ResponseEntity createAuthority(@RequestBody Authority authority){

        logger.debug("'/authorities' endpoint-dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'createAuthority' method-i ishga tushdi");

        try {
            Authority result = authorityService.save(authority);
            logger.info(authority.getPositionName() + " authority 'Authority' jadvaliga muvaffaqiyatli qo'shildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(authority.getPositionName() + " authority 'Authority' jadvaliga muvaffaqiyatsiz qo'shildi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/authorities")
    public ResponseEntity getAll(){

        logger.debug("'/authorities' endpoint- dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<Authority> result = authorityService.findAll();
            logger.info("List<Authority> turidagi data 'Authority' jadvalidan natija olindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<Authority> turidagi data 'Authority' jadvalidan natija olinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/authorities")
    public ResponseEntity delete(@RequestParam String positionName){

        logger.debug("'/authorities' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            authorityService.delete(positionName);
            logger.info(positionName + " 'Authority' javvaldian muvafaqqiyatli o'chirildi");
            return ResponseEntity.ok("PositionName is deleted successfully!");
        } catch (Exception e) {
            logger.error(positionName + " 'Authority' javvaldian muvafaqqiyatsiz o'chirildi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
