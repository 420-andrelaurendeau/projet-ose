package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.FileDto;
import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.InternshipmanagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/internshipManager")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class IntershipManagerController {

    private final InternshipmanagerService internshipmanagerService;
    private final InternOfferService offerJobService;

    @GetMapping("/offers")
    public ResponseEntity<Page<InternOfferDto>> getOffers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String state) {



        Page<InternOfferDto> internOfferDtos = internshipmanagerService.getSortedOffersByPage(page, size, state, sortField, sortDirection);


        return new ResponseEntity<>(internOfferDtos, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getCountInternOfferByState() {

        Map<String, Long> countInternOfferByState = offerJobService.getCountByState();

        return new ResponseEntity<>(countInternOfferByState, HttpStatus.OK);
    }


    @GetMapping("/offer/{id}")
    public ResponseEntity<InternOfferDto> getOfferById(@PathVariable Long id) {

        InternOfferDto internOfferDto = offerJobService.getById(id);

        return new ResponseEntity<>(internOfferDto, HttpStatus.OK);
    }

    @GetMapping("/studentCv/pending")
    public ResponseEntity<List<FileDto>> getPendingCv() {

        List<FileDto> pendingCv = internshipmanagerService.getPendingCv();

        return new ResponseEntity<>(pendingCv, HttpStatus.OK);
    }
}
