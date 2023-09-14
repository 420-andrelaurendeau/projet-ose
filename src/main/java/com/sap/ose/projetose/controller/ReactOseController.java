package com.sap.ose.projetose.controller;

import com.example.tp4h23initial.dto.*;
import com.example.tp4h23initial.model.Client;
import com.example.tp4h23initial.model.Document;
import com.example.tp4h23initial.model.Employe;
import com.example.tp4h23initial.model.Emprunt;
import com.example.tp4h23initial.service.BiblioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblio")
public class ReactOseController {
    Logger logger = LoggerFactory.getLogger(ReactOseController.class);

    private final BiblioService biblioService;
    public ReactOseController(BiblioService biblioService) {
        this.biblioService = biblioService;
    }

    @GetMapping("/clients")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ClientDto>> getBooks() {
        logger.info("getBooks");
        return ResponseEntity.ok().body(biblioService.getAllClientsDto());
    }

    @PostMapping("/clients")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        logger.info("addClient");
        return biblioService.createClient(client)
                .map(client1 -> ResponseEntity.status(HttpStatus.OK).body(client1))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/clients/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        logger.info("getClientById " + id);
        return biblioService.getClientDto(Math.toIntExact(id))
                .map(client -> ResponseEntity.status(HttpStatus.OK).body(client))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/clients/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        logger.info("updateClient " + id);
        return biblioService.getClient(Math.toIntExact(id))
                .map(client1 -> {
                    biblioService.updateClient(client);
                    return ResponseEntity.status(HttpStatus.OK).body(client);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/employees")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<EmployeDto>> getEmployees() {
        logger.info("getEmployees");
        return ResponseEntity.ok().body(biblioService.getAllEmployesDto());
    }

    @PostMapping("/employees")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employe> addEmployee(@RequestBody Employe employe) {
        logger.info("addEmployee");
        return biblioService.createEmploye(employe)
                .map(client1 -> ResponseEntity.status(HttpStatus.OK).body(client1))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/employees/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployeDto> getEmployeeById(@PathVariable Long id) {
        logger.info("getEmployeeById " + id);
        return biblioService.getEmployeDto(Math.toIntExact(id))
                .map(employe -> ResponseEntity.status(HttpStatus.OK).body(employe))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/employees/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employe> updateEmployee(@PathVariable Long id, @RequestBody Employe employe) {
        logger.info("updateEmployee " + id);
        return biblioService.getEmployeDto(Math.toIntExact(id))
                .map(employe1 -> {
                    biblioService.updateEmploye(employe);
                    return ResponseEntity.status(HttpStatus.OK).body(employe);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/clientsAndDocumentsS")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ClientsAndDocsDto>> getClientsAndDocuments() {
        logger.info("getClientsAndDocuments");
        return ResponseEntity.ok().body(biblioService.getClientsAndDocsS());
    }

    @GetMapping("/documents")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<DocumentDto>> getDocuments() {
        logger.info("getDocuments");
        return ResponseEntity.ok().body(biblioService.changeListDocument(biblioService.getAllDocuments()));
    }

    @GetMapping("/documents/available")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<DocumentDto>> getAvailableDocuments() {
        logger.info("getAvailableDocuments");
        return ResponseEntity.ok().body(biblioService.getAvailableDocuments());
    }

    @GetMapping("/documents/available/{recherche}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<DocumentDto>> getAvailableDocumentsByRecherche(@PathVariable String recherche) {
        logger.info("getAvailableDocumentsByRecherche");
        return ResponseEntity.ok().body(biblioService.searchAvailableDocuments(recherche));
    }

    @PostMapping("/documents")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Document> addDocument(@RequestBody DocumentDto document) {
        logger.info("addDocument");
        return biblioService.addDocument(document)
                .map(document1 -> ResponseEntity.status(HttpStatus.OK).body(document1))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/documents/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        logger.info("getDocumentById " + id);
        return biblioService.getDocument(Math.toIntExact(id))
                .map(document -> ResponseEntity.status(HttpStatus.OK).body(document))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/clients/{id}/documents")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Document>> getDocumentsByClientId(@PathVariable Long id) {
        logger.info("getDocumentsByClientId " + id);
        return biblioService.getClientDocument(Math.toIntExact(id))
                .map(documents -> ResponseEntity.status(HttpStatus.OK).body(documents))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/clients/{id}/documents/{documentId}/return")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity returnDocument(@PathVariable Long id, @PathVariable Long documentId) {
        logger.info("returnDocument " + id);
        return ResponseEntity.ok().body(biblioService.returnDocument(biblioService.getClientDto(Math.toIntExact(id)).get(), biblioService.getDocument(Math.toIntExact(documentId)).get()));
    }

    @PostMapping("/clients/{id}/documents")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Emprunt> addDocumentToClient(@PathVariable Long id, @RequestBody List<Integer> ids) {
        logger.info("addDocumentToEmployee " + id);
        return biblioService.createEmprunt(Math.toIntExact(id))
                .map(emprunt -> {
                    if (!ids.isEmpty()){
                        for (int identificateur : ids) {
                            biblioService.createEmpruntDocument(biblioService.getDocument(identificateur).get(), emprunt );
                        }
                        biblioService.addEmprunt(Math.toIntExact(id), emprunt);
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(emprunt);})
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



}
