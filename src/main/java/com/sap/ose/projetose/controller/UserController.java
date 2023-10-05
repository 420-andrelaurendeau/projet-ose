package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.UserDto;
import com.sap.ose.projetose.service.OseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UserController {
    private final OseService oseService;
    final Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    public UserController(OseService oseService) {
        this.oseService = oseService;
    }

    @GetMapping("/utilisateurs")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("getAllUsers");
        return ResponseEntity.ok().body(oseService.getAllUsers());
    }

}