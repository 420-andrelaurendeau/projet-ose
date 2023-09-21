package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EmployeurRepositary;
import com.sap.ose.projetose.repository.ProgrammeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

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
    private EmployeurRepositary employeurRepositary;

    @Autowired
    private OseService oseService;

    @MockBean
    private ProgrammeRepository programmeRepository;

    /**
     * Method under test: {@link OseService#saveEmployeur(EmployeurDTO)}
     */
    @Test
    void testSaveEmployeur() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setNomEntreprise("Nom Entreprise");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(1);
        when(employeurRepositary.save(Mockito.<Employeur>any())).thenReturn(employeur);
        Optional<EmployeurDTO> actualSaveEmployeurResult = oseService.saveEmployeur(
                new EmployeurDTO(1, "Nom", "Prenom", "jane.doe@example.org", "6625550144", "iloveyou", "Nom Entreprise", 1));
        assertTrue(actualSaveEmployeurResult.isPresent());
        EmployeurDTO getResult = actualSaveEmployeurResult.get();
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals(1, getResult.getProgramme());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("Nom Entreprise", getResult.getNomEntreprise());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getId());
        verify(employeurRepositary).save(Mockito.<Employeur>any());
    }

    /**
     * Method under test: {@link OseService#saveEmployeur(EmployeurDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveEmployeur2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sap.ose.projetose.dto.EmployeurDTO.fromDTO()" because "employeurDTO" is null
        //       at com.sap.ose.projetose.service.OseService.saveEmployeur(OseService.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setNomEntreprise("Nom Entreprise");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(1);
        when(employeurRepositary.save(Mockito.<Employeur>any())).thenReturn(employeur);
        oseService.saveEmployeur(null);
    }

    /**
     * Method under test: {@link OseService#saveEmployeur(EmployeurDTO)}
     */
    @Test
    void testSaveEmployeur3() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setNomEntreprise("Nom Entreprise");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(1);
        when(employeurRepositary.save(Mockito.<Employeur>any())).thenReturn(employeur);

        Employeur employeur2 = new Employeur();
        employeur2.setEmail("jane.doe@example.org");
        employeur2.setId(1);
        employeur2.setNom("Nom");
        employeur2.setNomEntreprise("Nom Entreprise");
        employeur2.setPassword("iloveyou");
        employeur2.setPhone("6625550144");
        employeur2.setPrenom("Prenom");
        employeur2.setProgramme(1);
        EmployeurDTO employeurDTO = mock(EmployeurDTO.class);
        when(employeurDTO.fromDTO()).thenReturn(employeur2);
        Optional<EmployeurDTO> actualSaveEmployeurResult = oseService.saveEmployeur(employeurDTO);
        assertTrue(actualSaveEmployeurResult.isPresent());
        EmployeurDTO getResult = actualSaveEmployeurResult.get();
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals(1, getResult.getProgramme());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("Nom Entreprise", getResult.getNomEntreprise());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getId());
        verify(employeurRepositary).save(Mockito.<Employeur>any());
        verify(employeurDTO).fromDTO();
    }

    /**
     * Method under test: {@link OseService#saveEmployeur(String, String, String, String, String, String, int)}
     */
    @Test
    void testSaveEmployeur4() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setNomEntreprise("Nom Entreprise");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(1);
        when(employeurRepositary.save(Mockito.<Employeur>any())).thenReturn(employeur);
        EmployeurDTO actualSaveEmployeurResult = oseService.saveEmployeur("Nom", "Prenom", "6625550144",
                "jane.doe@example.org", "iloveyou", "Nom Entreprise", 1);
        assertEquals("jane.doe@example.org", actualSaveEmployeurResult.getEmail());
        assertEquals(1, actualSaveEmployeurResult.getProgramme());
        assertEquals("Prenom", actualSaveEmployeurResult.getPrenom());
        assertEquals("6625550144", actualSaveEmployeurResult.getPhone());
        assertEquals("iloveyou", actualSaveEmployeurResult.getPassword());
        assertEquals("Nom Entreprise", actualSaveEmployeurResult.getNomEntreprise());
        assertEquals("Nom", actualSaveEmployeurResult.getNom());
        assertEquals(1, actualSaveEmployeurResult.getId());
        verify(employeurRepositary).save(Mockito.<Employeur>any());
    }

    /**
     * Method under test: {@link OseService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur() {
        when(employeurRepositary.findAll()).thenReturn(new ArrayList<>());
        assertTrue(oseService.getAllEmployeur().isEmpty());
        verify(employeurRepositary).findAll();
    }

    /**
     * Method under test: {@link OseService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur2() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setNomEntreprise("Nom Entreprise");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(1);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(employeurRepositary.findAll()).thenReturn(employeurList);
        assertEquals(1, oseService.getAllEmployeur().size());
        verify(employeurRepositary).findAll();
    }

    /**
     * Method under test: {@link OseService#getEmployeurById(int)}
     */
    @Test
    void testGetEmployeurById() {
        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setId(1);
        employeur.setNom("Nom");
        employeur.setNomEntreprise("Nom Entreprise");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(1);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepositary.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        EmployeurDTO actualEmployeurById = oseService.getEmployeurById(1);
        assertEquals("jane.doe@example.org", actualEmployeurById.getEmail());
        assertEquals(1, actualEmployeurById.getProgramme());
        assertEquals("Prenom", actualEmployeurById.getPrenom());
        assertEquals("6625550144", actualEmployeurById.getPhone());
        assertEquals("iloveyou", actualEmployeurById.getPassword());
        assertEquals("Nom Entreprise", actualEmployeurById.getNomEntreprise());
        assertEquals("Nom", actualEmployeurById.getNom());
        assertEquals(1, actualEmployeurById.getId());
        verify(employeurRepositary).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OseService#getEmployeurById(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEmployeurById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sap.ose.projetose.modeles.Employeur.getId()" because "employeur" is null
        //       at com.sap.ose.projetose.dto.EmployeurDTO.<init>(EmployeurDTO.java:22)
        //       at com.sap.ose.projetose.service.OseService.getEmployeurById(OseService.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        Optional<Employeur> emptyResult = Optional.empty();
        when(employeurRepositary.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        oseService.getEmployeurById(1);
    }

    /**
     * Method under test: {@link OseService#saveProgramme(ProgrammeDTO)}
     */
    @Test
    void testSaveProgramme() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");
        when(programmeRepository.save(Mockito.<Programme>any())).thenReturn(programme);
        Optional<ProgrammeDTO> actualSaveProgrammeResult = oseService
                .saveProgramme(new ProgrammeDTO(1, "Nom", "The characteristics of someone or something"));
        assertTrue(actualSaveProgrammeResult.isPresent());
        ProgrammeDTO getResult = actualSaveProgrammeResult.get();
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getId());
        verify(programmeRepository).save(Mockito.<Programme>any());
    }

    /**
     * Method under test: {@link OseService#saveProgramme(ProgrammeDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveProgramme2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.sap.ose.projetose.dto.ProgrammeDTO.fromDto()" because "programmeDTO" is null
        //       at com.sap.ose.projetose.service.OseService.saveProgramme(OseService.java:51)
        //   See https://diff.blue/R013 to resolve this issue.

        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");
        when(programmeRepository.save(Mockito.<Programme>any())).thenReturn(programme);
        oseService.saveProgramme(null);
    }

    /**
     * Method under test: {@link OseService#saveProgramme(ProgrammeDTO)}
     */
    @Test
    void testSaveProgramme3() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");
        when(programmeRepository.save(Mockito.<Programme>any())).thenReturn(programme);

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1);
        programme2.setNom("Nom");
        ProgrammeDTO programmeDTO = mock(ProgrammeDTO.class);
        when(programmeDTO.fromDto()).thenReturn(programme2);
        Optional<ProgrammeDTO> actualSaveProgrammeResult = oseService.saveProgramme(programmeDTO);
        assertTrue(actualSaveProgrammeResult.isPresent());
        ProgrammeDTO getResult = actualSaveProgrammeResult.get();
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getId());
        verify(programmeRepository).save(Mockito.<Programme>any());
        verify(programmeDTO).fromDto();
    }

    /**
     * Method under test: {@link OseService#saveProgramme(String, String)}
     */
    @Test
    void testSaveProgramme4() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");
        when(programmeRepository.save(Mockito.<Programme>any())).thenReturn(programme);
        ProgrammeDTO actualSaveProgrammeResult = oseService.saveProgramme("Nom",
                "The characteristics of someone or something");
        assertEquals("The characteristics of someone or something", actualSaveProgrammeResult.getDescription());
        assertEquals("Nom", actualSaveProgrammeResult.getNom());
        assertEquals(1, actualSaveProgrammeResult.getId());
        verify(programmeRepository).save(Mockito.<Programme>any());
    }

    /**
     * Method under test: {@link OseService#getProgrammes()}
     */
    @Test
    void testGetProgrammes() {
        when(programmeRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(oseService.getProgrammes().isEmpty());
        verify(programmeRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getProgrammes()}
     */
    @Test
    void testGetProgrammes2() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1);
        programme.setNom("Nom");

        ArrayList<Programme> programmeList = new ArrayList<>();
        programmeList.add(programme);
        when(programmeRepository.findAll()).thenReturn(programmeList);
        List<ProgrammeDTO> actualProgrammes = oseService.getProgrammes();
        assertEquals(1, actualProgrammes.size());
        ProgrammeDTO getResult = actualProgrammes.get(0);
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getId());
        verify(programmeRepository).findAll();
    }
}

