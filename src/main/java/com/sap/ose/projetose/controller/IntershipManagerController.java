package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.InternshipmanagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intershipManager")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class IntershipManagerController {

    private final InternshipmanagerService internshipmanagerService;

    @GetMapping("/offers")
    public ResponseEntity<List<InternOfferDto>> getOffers() {

        List<InternOfferDto> internOfferDtos = internshipmanagerService.getOffers();

        return new ResponseEntity<>(internOfferDtos, HttpStatus.OK);
    }
}
