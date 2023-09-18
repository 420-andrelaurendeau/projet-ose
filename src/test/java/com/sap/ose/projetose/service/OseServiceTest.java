package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EtudiantDTO;
import com.sap.ose.projetose.dto.ProgrammeDTO;
import com.sap.ose.projetose.modeles.Etudiant;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.repository.EtudiantRepository;
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
    private EtudiantRepository etudiantRepository;

    @Autowired
    private OseService oseService;

    @MockBean
    private ProgrammeRepository programmeRepository;

    /**
     * Method under test: {@link OseService#saveEtudiant(EtudiantDTO)}
     */
    @Test
    void testSaveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule(1);
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(1);
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant);
        Optional<EtudiantDTO> actualSaveEtudiantResult = oseService.saveEtudiant(
                new EtudiantDTO(1, "Nom", "Prenom", "jane.doe@example.org", "6625550144", "iloveyou", 1, 1, "Cv"));
        assertTrue(actualSaveEtudiantResult.isPresent());
        EtudiantDTO getResult = actualSaveEtudiantResult.get();
        assertEquals("Cv", getResult.getCv());
        assertEquals(1, getResult.getProgramme());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getMatricule());
        assertEquals(1, getResult.getId());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        verify(etudiantRepository).save(Mockito.<Etudiant>any());
    }


    /**
     * Method under test: {@link OseService#saveEtudiant(EtudiantDTO)}
     */
    @Test
    void testSaveEtudiant2() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule(1);
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(1);
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setCv("Cv");
        etudiant2.setEmail("jane.doe@example.org");
        etudiant2.setId(1);
        etudiant2.setMatricule(1);
        etudiant2.setNom("Nom");
        etudiant2.setPassword("iloveyou");
        etudiant2.setPhone("6625550144");
        etudiant2.setPrenom("Prenom");
        etudiant2.setProgramme(1);
        EtudiantDTO etudiantDTO = mock(EtudiantDTO.class);
        when(etudiantDTO.fromDto()).thenReturn(etudiant2);
        Optional<EtudiantDTO> actualSaveEtudiantResult = oseService.saveEtudiant(etudiantDTO);
        assertTrue(actualSaveEtudiantResult.isPresent());
        EtudiantDTO getResult = actualSaveEtudiantResult.get();
        assertEquals("Cv", getResult.getCv());
        assertEquals(1, getResult.getProgramme());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("Nom", getResult.getNom());
        assertEquals(1, getResult.getMatricule());
        assertEquals(1, getResult.getId());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        verify(etudiantRepository).save(Mockito.<Etudiant>any());
        verify(etudiantDTO).fromDto();
    }

    /**
     * Method under test: {@link OseService#saveEtudiant(String, String, String, String, String, int, String)}
     */
    @Test
    void testSaveEtudiant3() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule(1);
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(1);
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant);
        EtudiantDTO actualSaveEtudiantResult = oseService.saveEtudiant("Nom", "Prenom", "jane.doe@example.org",
                "iloveyou", "6625550144", 1, "Cv");
        assertEquals("Cv", actualSaveEtudiantResult.getCv());
        assertEquals(1, actualSaveEtudiantResult.getProgramme());
        assertEquals("Prenom", actualSaveEtudiantResult.getPrenom());
        assertEquals("6625550144", actualSaveEtudiantResult.getPhone());
        assertEquals("iloveyou", actualSaveEtudiantResult.getPassword());
        assertEquals("Nom", actualSaveEtudiantResult.getNom());
        assertEquals(1, actualSaveEtudiantResult.getMatricule());
        assertEquals(1, actualSaveEtudiantResult.getId());
        assertEquals("jane.doe@example.org", actualSaveEtudiantResult.getEmail());
        verify(etudiantRepository).save(Mockito.<Etudiant>any());
    }

    /**
     * Method under test: {@link OseService#getEtudiants()}
     */
    @Test
    void testGetEtudiants() {
        when(etudiantRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(oseService.getEtudiants().isEmpty());
        verify(etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link OseService#getEtudiants()}
     */
    @Test
    void testGetEtudiants2() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule(1);
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(1);

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiantList);
        assertEquals(1, oseService.getEtudiants().size());
        verify(etudiantRepository).findAll();
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
    void testSaveProgramme2() {
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
    void testSaveProgramme3() {
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

    /**
     * Method under test: {@link OseService#getEtudiantById(int)}
     */
    @Test
    void testGetEtudiantById() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCv("Cv");
        etudiant.setEmail("jane.doe@example.org");
        etudiant.setId(1);
        etudiant.setMatricule(1);
        etudiant.setNom("Nom");
        etudiant.setPassword("iloveyou");
        etudiant.setPhone("6625550144");
        etudiant.setPrenom("Prenom");
        etudiant.setProgramme(1);
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        when(etudiantRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        EtudiantDTO actualEtudiantById = oseService.getEtudiantById(1);
        assertEquals("Cv", actualEtudiantById.getCv());
        assertEquals(1, actualEtudiantById.getProgramme());
        assertEquals("Prenom", actualEtudiantById.getPrenom());
        assertEquals("6625550144", actualEtudiantById.getPhone());
        assertEquals("iloveyou", actualEtudiantById.getPassword());
        assertEquals("Nom", actualEtudiantById.getNom());
        assertEquals(1, actualEtudiantById.getMatricule());
        assertEquals(1, actualEtudiantById.getId());
        assertEquals("jane.doe@example.org", actualEtudiantById.getEmail());
        verify(etudiantRepository).findById(Mockito.<Integer>any());
    }

}

