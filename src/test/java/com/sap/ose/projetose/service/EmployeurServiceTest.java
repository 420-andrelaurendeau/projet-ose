package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EmployerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.InternOffer;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.repository.EmployeurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeurService.class})
@ExtendWith(SpringExtension.class)
class EmployeurServiceTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @Autowired
    private EmployeurService employeurService;

    @MockBean
    private ProgrammeService programmeService;

    /**
     * Method under test: {@link EmployeurService#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Employeur actualFindByEmailResult = employeurService.findByEmail("jane.doe@example.org");
        verify(employeurRepository).findByEmail(Mockito.<String>any());
        assertSame(employeur, actualFindByEmailResult);
    }

    /**
     * Method under test: {@link EmployeurService#findByEmail(String)}
     */
    @Test
    void testFindByEmail2() {
        when(employeurRepository.findByEmail(Mockito.<String>any()))
                .thenThrow(new EmployerNotFoundException("An error occurred"));
        assertThrows(EmployerNotFoundException.class, () -> employeurService.findByEmail("jane.doe@example.org"));
        verify(employeurRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Employeur actualFindByIdResult = employeurService.findById(1L);
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertSame(employeur, actualFindByIdResult);
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById2() {
        when(employeurRepository.findById(Mockito.<Long>any()))
                .thenThrow(new EmployerNotFoundException("An error occurred"));
        assertThrows(EmployerNotFoundException.class, () -> employeurService.findById(1L));
        verify(employeurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById3() {
        when(employeurRepository.findById(Mockito.<Long>any())).thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(DatabaseException.class, () -> employeurService.findById(1L));
        verify(employeurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById4() {
        when(employeurRepository.findById(Mockito.<Long>any())).thenThrow(new DatabaseException("Employeur non trouvé"));
        assertThrows(ServiceException.class, () -> employeurService.findById(1L));
        verify(employeurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeurService#findAllEmployeur()}
     */
    @Test
    void testFindAllEmployeur() {
        ArrayList<Employeur> employeurList = new ArrayList<>();
        when(employeurRepository.findAll()).thenReturn(employeurList);
        List<Employeur> actualFindAllEmployeurResult = employeurService.findAllEmployeur();
        verify(employeurRepository).findAll();
        assertTrue(actualFindAllEmployeurResult.isEmpty());
        assertSame(employeurList, actualFindAllEmployeurResult);
    }

    /**
     * Method under test: {@link EmployeurService#findAllEmployeur()}
     */
    @Test
    void testFindAllEmployeur2() {
        when(employeurRepository.findAll()).thenThrow(new EmployerNotFoundException("An error occurred"));
        assertThrows(EmployerNotFoundException.class, () -> employeurService.findAllEmployeur());
        verify(employeurRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeurService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur() {
        when(employeurRepository.findAll()).thenReturn(new ArrayList<>());
        List<EmployeurDto> actualAllEmployeur = employeurService.getAllEmployeur();
        verify(employeurRepository).findAll();
        assertTrue(actualAllEmployeur.isEmpty());
    }

    /**
     * Method under test: {@link EmployeurService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur2() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(employeurRepository.findAll()).thenReturn(employeurList);
        List<EmployeurDto> actualAllEmployeur = employeurService.getAllEmployeur();
        verify(employeurRepository).findAll();
        assertEquals(1, actualAllEmployeur.size());
    }

    /**
     * Method under test: {@link EmployeurService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur3() {
        when(employeurRepository.findAll()).thenThrow(new EmployerNotFoundException("An error occurred"));
        assertThrows(EmployerNotFoundException.class, () -> employeurService.getAllEmployeur());
        verify(employeurRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeurService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur4() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");
        Employeur employeur = mock(Employeur.class);
        when(employeur.getEntreprise()).thenReturn("Entreprise");
        when(employeur.getEmail()).thenReturn("jane.doe@example.org");
        when(employeur.getNom()).thenReturn("Nom");
        when(employeur.getPhone()).thenReturn("6625550144");
        when(employeur.getPrenom()).thenReturn("Prenom");
        when(employeur.getId()).thenReturn(1L);
        doNothing().when(employeur).setEntreprise(Mockito.<String>any());
        doNothing().when(employeur).setInternOffers(Mockito.<List<InternOffer>>any());
        doNothing().when(employeur).setProgramme(Mockito.<Programme>any());
        doNothing().when(employeur).setEmail(Mockito.<String>any());
        doNothing().when(employeur).setId(anyLong());
        doNothing().when(employeur).setNom(Mockito.<String>any());
        doNothing().when(employeur).setPassword(Mockito.<String>any());
        doNothing().when(employeur).setPhone(Mockito.<String>any());
        doNothing().when(employeur).setPrenom(Mockito.<String>any());
        doNothing().when(employeur).setRole(Mockito.<Role>any());
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);

        ArrayList<Employeur> employeurList = new ArrayList<>();
        employeurList.add(employeur);
        when(employeurRepository.findAll()).thenReturn(employeurList);
        List<EmployeurDto> actualAllEmployeur = employeurService.getAllEmployeur();
        verify(employeur).getEntreprise();
        verify(employeur).setEntreprise(Mockito.<String>any());
        verify(employeur).setInternOffers(Mockito.<List<InternOffer>>any());
        verify(employeur).setProgramme(Mockito.<Programme>any());
        verify(employeur).getEmail();
        verify(employeur, atLeast(1)).getId();
        verify(employeur).getNom();
        verify(employeur).getPhone();
        verify(employeur).getPrenom();
        verify(employeur).setEmail(Mockito.<String>any());
        verify(employeur).setId(anyLong());
        verify(employeur).setNom(Mockito.<String>any());
        verify(employeur).setPassword(Mockito.<String>any());
        verify(employeur).setPhone(Mockito.<String>any());
        verify(employeur).setPrenom(Mockito.<String>any());
        verify(employeur).setRole(Mockito.<Role>any());
        verify(employeurRepository).findAll();
        assertEquals(1, actualAllEmployeur.size());
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Employeur employeur = new Employeur();
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EmployeurDto actualEmployeurById = employeurService.getEmployeurById(1L);
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertEquals("6625550144", actualEmployeurById.getPhone());
        assertEquals("Entreprise", actualEmployeurById.getEntreprise());
        assertEquals("Nom", actualEmployeurById.getNom());
        assertEquals("Prenom", actualEmployeurById.getPrenom());
        assertEquals("jane.doe@example.org", actualEmployeurById.getEmail());
        assertEquals(1L, actualEmployeurById.getProgramme_id());
        assertEquals(1L, actualEmployeurById.getId());
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById2() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");

        Programme programme2 = new Programme();
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");
        Employeur employeur = mock(Employeur.class);
        when(employeur.getProgramme()).thenReturn(programme2);
        when(employeur.getEntreprise()).thenReturn("Entreprise");
        when(employeur.getEmail()).thenReturn("jane.doe@example.org");
        when(employeur.getNom()).thenReturn("Nom");
        when(employeur.getPhone()).thenReturn("6625550144");
        when(employeur.getPrenom()).thenReturn("Prenom");
        when(employeur.getId()).thenReturn(1L);
        doNothing().when(employeur).setEntreprise(Mockito.<String>any());
        doNothing().when(employeur).setInternOffers(Mockito.<List<InternOffer>>any());
        doNothing().when(employeur).setProgramme(Mockito.<Programme>any());
        doNothing().when(employeur).setEmail(Mockito.<String>any());
        doNothing().when(employeur).setId(anyLong());
        doNothing().when(employeur).setNom(Mockito.<String>any());
        doNothing().when(employeur).setPassword(Mockito.<String>any());
        doNothing().when(employeur).setPhone(Mockito.<String>any());
        doNothing().when(employeur).setPrenom(Mockito.<String>any());
        doNothing().when(employeur).setRole(Mockito.<Role>any());
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EmployeurDto actualEmployeurById = employeurService.getEmployeurById(1L);
        verify(employeur).getEntreprise();
        verify(employeur).getProgramme();
        verify(employeur).setEntreprise(Mockito.<String>any());
        verify(employeur).setInternOffers(Mockito.<List<InternOffer>>any());
        verify(employeur).setProgramme(Mockito.<Programme>any());
        verify(employeur).getEmail();
        verify(employeur).getId();
        verify(employeur).getNom();
        verify(employeur).getPhone();
        verify(employeur).getPrenom();
        verify(employeur).setEmail(Mockito.<String>any());
        verify(employeur).setId(anyLong());
        verify(employeur).setNom(Mockito.<String>any());
        verify(employeur).setPassword(Mockito.<String>any());
        verify(employeur).setPhone(Mockito.<String>any());
        verify(employeur).setPrenom(Mockito.<String>any());
        verify(employeur).setRole(Mockito.<Role>any());
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertEquals("6625550144", actualEmployeurById.getPhone());
        assertEquals("Entreprise", actualEmployeurById.getEntreprise());
        assertEquals("Nom", actualEmployeurById.getNom());
        assertEquals("Prenom", actualEmployeurById.getPrenom());
        assertEquals("jane.doe@example.org", actualEmployeurById.getEmail());
        assertEquals(1L, actualEmployeurById.getProgramme_id());
        assertEquals(1L, actualEmployeurById.getId());
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById3() {
        Programme programme = new Programme();
        programme.setDescription("The characteristics of someone or something");
        programme.setId(1L);
        programme.setNom("Nom");
        Programme programme2 = mock(Programme.class);
        when(programme2.getId()).thenReturn(1L);
        doNothing().when(programme2).setDescription(Mockito.<String>any());
        doNothing().when(programme2).setId(anyLong());
        doNothing().when(programme2).setNom(Mockito.<String>any());
        programme2.setDescription("The characteristics of someone or something");
        programme2.setId(1L);
        programme2.setNom("Nom");
        Employeur employeur = mock(Employeur.class);
        when(employeur.getProgramme()).thenReturn(programme2);
        when(employeur.getEntreprise()).thenReturn("Entreprise");
        when(employeur.getEmail()).thenReturn("jane.doe@example.org");
        when(employeur.getNom()).thenReturn("Nom");
        when(employeur.getPhone()).thenReturn("6625550144");
        when(employeur.getPrenom()).thenReturn("Prenom");
        when(employeur.getId()).thenReturn(1L);
        doNothing().when(employeur).setEntreprise(Mockito.<String>any());
        doNothing().when(employeur).setInternOffers(Mockito.<List<InternOffer>>any());
        doNothing().when(employeur).setProgramme(Mockito.<Programme>any());
        doNothing().when(employeur).setEmail(Mockito.<String>any());
        doNothing().when(employeur).setId(anyLong());
        doNothing().when(employeur).setNom(Mockito.<String>any());
        doNothing().when(employeur).setPassword(Mockito.<String>any());
        doNothing().when(employeur).setPhone(Mockito.<String>any());
        doNothing().when(employeur).setPrenom(Mockito.<String>any());
        doNothing().when(employeur).setRole(Mockito.<Role>any());
        employeur.setEmail("jane.doe@example.org");
        employeur.setEntreprise("Entreprise");
        employeur.setId(1L);
        employeur.setInternOffers(new ArrayList<>());
        employeur.setNom("Nom");
        employeur.setPassword("iloveyou");
        employeur.setPhone("6625550144");
        employeur.setPrenom("Prenom");
        employeur.setProgramme(programme);
        employeur.setRole(Role.employer);
        Optional<Employeur> ofResult = Optional.of(employeur);
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        EmployeurDto actualEmployeurById = employeurService.getEmployeurById(1L);
        verify(employeur).getEntreprise();
        verify(employeur).getProgramme();
        verify(employeur).setEntreprise(Mockito.<String>any());
        verify(employeur).setInternOffers(Mockito.<List<InternOffer>>any());
        verify(employeur).setProgramme(Mockito.<Programme>any());
        verify(programme2).getId();
        verify(programme2).setDescription(Mockito.<String>any());
        verify(programme2).setId(anyLong());
        verify(programme2).setNom(Mockito.<String>any());
        verify(employeur).getEmail();
        verify(employeur).getId();
        verify(employeur).getNom();
        verify(employeur).getPhone();
        verify(employeur).getPrenom();
        verify(employeur).setEmail(Mockito.<String>any());
        verify(employeur).setId(anyLong());
        verify(employeur).setNom(Mockito.<String>any());
        verify(employeur).setPassword(Mockito.<String>any());
        verify(employeur).setPhone(Mockito.<String>any());
        verify(employeur).setPrenom(Mockito.<String>any());
        verify(employeur).setRole(Mockito.<Role>any());
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertEquals("6625550144", actualEmployeurById.getPhone());
        assertEquals("Entreprise", actualEmployeurById.getEntreprise());
        assertEquals("Nom", actualEmployeurById.getNom());
        assertEquals("Prenom", actualEmployeurById.getPrenom());
        assertEquals("jane.doe@example.org", actualEmployeurById.getEmail());
        assertEquals(1L, actualEmployeurById.getProgramme_id());
        assertEquals(1L, actualEmployeurById.getId());
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById4() {
        Optional<Employeur> emptyResult = Optional.empty();
        when(employeurRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        EmployeurDto actualEmployeurById = employeurService.getEmployeurById(1L);
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertNull(actualEmployeurById);
    }
}
