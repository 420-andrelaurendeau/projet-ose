package com.sap.ose.projetose.controller;


import com.sap.ose.projetose.dto.InternOfferDto;
import com.sap.ose.projetose.dto.ProgrammeDto;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.service.InternOfferService;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interOfferJob")
@CrossOrigin(origins = "http://localhost:3000")
public class InternOfferController {

    private final InternOfferService offerJobService;

    @Autowired
    public InternOfferController(InternOfferService offerJobService) {
        this.offerJobService = offerJobService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employer')")
    public ResponseEntity<InternOfferDto> saveInterOfferJob(@RequestBody InternOfferDto internOfferJobdto) {
        InternOfferDto savedOfferJobDto = offerJobService.saveInterOfferJob(internOfferJobdto);

        return new ResponseEntity<>(savedOfferJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/pendingOffers")
    @PreAuthorize("hasAuthority('internshipmanager')")
    public List<InternOfferDto> getPendingOffers() {
        return offerJobService.getInternOfferPending();
    }

    @GetMapping("/allOffers")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public List<InternOfferDto> getAllOffers() {
        System.out.println("get all");
        return offerJobService.getAllInternOffers();
    }

    @GetMapping("/student/season/{selectedOption}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public List<InternOfferDto> getStudentOffersBySeason(@PathVariable String selectedOption) {
        return offerJobService.getStudentOfferBySeason(selectedOption);
    }

    @GetMapping("/${email}/season/${selectedOption}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('student')")
    public List<InternOfferDto> getEmployeurOffersBySeason(@PathVariable String selectedOption, @PathVariable String email ) {
        return offerJobService.getEmployeurOfferBySeason(selectedOption,email);
    }

    @GetMapping("/${email}/getSeason")
    public ResponseEntity<List<String>> getEmployeurSeasons(@PathVariable String email){
        List<String> seasons = offerJobService.getEmployeurSeasonsOffers(email);
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }

    @GetMapping("/OffersEtudiant")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employer') OR hasAuthority('student')")
    public List<InternOfferDto> getOffersEtudiant() {
        List<InternOfferDto> offers = offerJobService.getInternOfferAccepted();
        return offers;
    }

    @GetMapping("/OffersEmp/{email}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employer')")
    public Page<InternOfferDto> getInternOfferJob(
            @PathVariable String email,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sortField,
            @RequestParam(required = false, defaultValue = "desc") String sortDirection) {

        return offerJobService.getInternOfferByEmployeurEmail(email, page, size, sortField, sortDirection);
    }

    @GetMapping("/offersEmployeur/{email}")
    @PreAuthorize("hasAuthority('internshipmanager') OR hasAuthority('employer')")
    public List<InternOfferDto> getInternOfferJob(
            @PathVariable String email) {
        return offerJobService.getOffersByEmployeurEmail(email);
    }


    @GetMapping("/getAllPossibleSeasons")
    public ResponseEntity<List<String>> getAllSeasons(){
        List<String> seasons = offerJobService.getAllSeasons();
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }
}

