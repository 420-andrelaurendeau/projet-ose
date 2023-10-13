package com.sap.ose.projetose.controller;

import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.service.InternOfferService;
import com.sap.ose.projetose.service.InternshipmanagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<InternOfferDto>> getOffers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String state) {

        Page<InternOfferDto> internOfferDtos;

        if (state == null){
            System.out.print("state is null");
                    internOfferDtos = internshipmanagerService.getOffers(page, size);

        }
        else {
            System.out.print(state);
            internOfferDtos = internshipmanagerService.getOffers(page, size, state);
        }


        return new ResponseEntity<>(internOfferDtos, HttpStatus.OK);
    }
}
