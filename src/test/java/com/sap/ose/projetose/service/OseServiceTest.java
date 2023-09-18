package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.dto.EtudiantDto;
import com.sap.ose.projetose.dto.UtilisateurDto;
import com.sap.ose.projetose.model.Employeur;
import com.sap.ose.projetose.model.Etudiant;
import com.sap.ose.projetose.repository.EmployeurRepository;
import com.sap.ose.projetose.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OseService.class})
@ExtendWith(SpringExtension.class)
class OseServiceTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @MockBean
    private EtudiantRepository etudiantRepository;

    @Autowired
    private OseService oseService;

    /**
     * Method under test: {@link OseService#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        when(employeurRepository.findAll()).thenReturn(new ArrayList<>());
        when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(oseService.getAllUsers().isEmpty());
        verify(employeurRepository).findAll();
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(employeurRepository.findAll()).thenReturn(employeurList);
        when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
        List<UtilisateurDto> actualAllUsers = oseService.getAllUsers();
        assertEquals(1, actualAllUsers.size());
        UtilisateurDto getResult = actualAllUsers.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("Nom", getResult.getNom());
        assertEquals("Entreprise", ((EmployeurDto) getResult).getEntreprise());
        verify(employeurRepository).findAll();
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getAllUsers()}
     */
    @Test
    void testGetAllUsers3() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("john.smith@example.org");
        employeur2.setEntreprise("com.sap.ose.projetose.model.Employeur");
        employeur2.setId(2);
        employeur2.setNom("com.sap.ose.projetose.model.Employeur");
        employeur2.setPassword("Password");
        employeur2.setPhone("8605550118");
        employeur2.setPrenom("com.sap.ose.projetose.model.Employeur");

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur2);
        employeurList.add(employeur);
        when(employeurRepository.findAll()).thenReturn(employeurList);
        when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
        List<UtilisateurDto> actualAllUsers = oseService.getAllUsers();
        assertEquals(2, actualAllUsers.size());
        UtilisateurDto getResult = actualAllUsers.get(0);
        assertEquals("com.sap.ose.projetose.model.Employeur", getResult.getPrenom());
        UtilisateurDto getResult2 = actualAllUsers.get(1);
        assertEquals("Prenom", getResult2.getPrenom());
        assertEquals("6625550144", getResult2.getPhone());
        assertEquals("Nom", getResult2.getNom());
        assertEquals("Entreprise", ((EmployeurDto) getResult2).getEntreprise());
        assertEquals("jane.doe@example.org", getResult2.getEmail());
        assertEquals("8605550118", getResult.getPhone());
        assertEquals("com.sap.ose.projetose.model.Employeur", getResult.getNom());
        assertEquals("com.sap.ose.projetose.model.Employeur", ((EmployeurDto) getResult).getEntreprise());
        assertEquals("john.smith@example.org", getResult.getEmail());
        verify(employeurRepository).findAll();
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getAllUsers()}
     */
    @Test
    void testGetAllUsers4() {
        when(employeurRepository.findAll()).thenReturn(new ArrayList<>());

        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme("Programme");

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        List<UtilisateurDto> actualAllUsers = oseService.getAllUsers();
        assertEquals(1, actualAllUsers.size());
        UtilisateurDto getResult = actualAllUsers.get(0);
        assertEquals("Cv", ((EtudiantDto) getResult).getCv());
        assertEquals("Programme", ((EtudiantDto) getResult).getProgramme());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("Nom", getResult.getNom());
        assertEquals("Matricule", ((EtudiantDto) getResult).getMatricule());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        verify(employeurRepository).findAll();
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getAllUsers()}
     */
    @Test
    void testGetAllUsers5() {
        when(employeurRepository.findAll()).thenReturn(new ArrayList<>());

        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme("Programme");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv("");
        etudiant2.setEmail("john.smith@example.org");
        etudiant2.setId(2);
        etudiant2.setMatricule("");
        etudiant2.setNom("");
        etudiant2.setPassword("Password");
        etudiant2.setPhone("8605550118");
        etudiant2.setPrenom("");
        etudiant2.setProgramme("");

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant2);
        etudiantList.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        List<UtilisateurDto> actualAllUsers = oseService.getAllUsers();
        assertEquals(2, actualAllUsers.size());
        UtilisateurDto getResult = actualAllUsers.get(0);
        assertEquals("", ((EtudiantDto) getResult).getProgramme());
        UtilisateurDto getResult2 = actualAllUsers.get(1);
        assertEquals("Programme", ((EtudiantDto) getResult2).getProgramme());
        assertEquals("Prenom", getResult2.getPrenom());
        assertEquals("6625550144", getResult2.getPhone());
        assertEquals("Nom", getResult2.getNom());
        assertEquals("Matricule", ((EtudiantDto) getResult2).getMatricule());
        assertEquals("jane.doe@example.org", getResult2.getEmail());
        assertEquals("Cv", ((EtudiantDto) getResult2).getCv());
        assertEquals("", getResult.getPrenom());
        assertEquals("8605550118", getResult.getPhone());
        assertEquals("", getResult.getNom());
        assertEquals("", ((EtudiantDto) getResult).getMatricule());
        assertEquals("john.smith@example.org", getResult.getEmail());
        assertEquals("", ((EtudiantDto) getResult).getCv());
        verify(employeurRepository).findAll();
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme("Programme");
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv("Cv");
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1);
        etudiant2.setMatricule("Matricule");
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme("Programme");
        oseService.saveEtudiant(etudiant2);
        verify(etudiantRepository).save(Mockito.<Etudiant>any());
        assertEquals("Cv", etudiant2.getCv());
        assertEquals("Programme", etudiant2.getProgramme());
        assertEquals("Prenom", etudiant2.getPrenom());
        assertEquals("6625550144", etudiant2.getPhone());
        assertEquals("iloveyou", etudiant2.getPassword());
        assertEquals("Nom", etudiant2.getNom());
        assertEquals("Matricule", etudiant2.getMatricule());
        assertEquals(1, etudiant2.getId());
        assertEquals("jane.doe@example.org", etudiant2.getEmail());
    }

    /**
     * Method under test: {@link OseService#getEtudiantById(Long)}
     */
    @Test
    void testGetEtudiantById() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme("Programme");
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        when(etudiantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(etudiant, oseService.getEtudiantById(1L));
        verify(etudiantRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OseService#getEtudiantByCourriel(String)}
     */
    @Test
    void testGetEtudiantByCourriel() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme("Programme");
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        when(etudiantRepository.findByCourriel(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(etudiant, oseService.getEtudiantByCourriel("Courriel"));
        verify(etudiantRepository).findByCourriel(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OseService#getAllEtudiants()}
     */
    @Test
    void testGetAllEtudiants() {
        when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(oseService.getAllEtudiants().isEmpty());
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getAllEtudiants()}
     */
    @Test
    void testGetAllEtudiants2() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule("Matricule");
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme("Programme");

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        assertEquals(1, oseService.getAllEtudiants().size());
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#saveEmployeur(Employeur)}
     */
    @Test
    void testSaveEmployeur() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        when(employeurRepository.save(Mockito.<Employeur>any())).thenReturn(employeur);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setEntreprise("Entreprise");
        employeur2.setId(1);
        employeur2.setNom("Nom");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        oseService.saveEmployeur(employeur2);
        verify(employeurRepository).save(Mockito.<Employeur>any());
        assertEquals("jane.doe@example.org", employeur2.getEmail());
        assertEquals("Prenom", employeur2.getPrenom());
        assertEquals("6625550144", employeur2.getPhone());
        assertEquals("iloveyou", employeur2.getPassword());
        assertEquals("Nom", employeur2.getNom());
        assertEquals(1, employeur2.getId());
        assertEquals("Entreprise", employeur2.getEntreprise());
    }
}

