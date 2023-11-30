package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sap.ose.projetose.dto.EmployeurDto;
import com.sap.ose.projetose.exception.DatabaseException;
import com.sap.ose.projetose.exception.EmployerNotFoundException;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.modeles.Programme;
import com.sap.ose.projetose.modeles.Role;
import com.sap.ose.projetose.repository.EmployeurRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EmployeurServiceDiffblueTest {
    @MockBean
    private EmployeurRepository employeurRepository;

    @MockBean
    private ProgrammeService programmeService;

    @Autowired
    private EmployeurService employeurService;

    /**
     * Method under test: {@link EmployeurService#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
        // Arrange, Act and Assert
        assertNull(employeurService.findByEmail("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById() {
        // Arrange
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

        // Act
        Employeur actualFindByIdResult = employeurService.findById(1L);

        // Assert
        verify(employeurRepository).findById(Mockito.<Long>any());
        assertSame(employeur, actualFindByIdResult);
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById2() {
        // Arrange
        when(employeurRepository.findById(Mockito.<Long>any()))
                .thenThrow(new EmployerNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EmployerNotFoundException.class, () -> employeurService.findById(1L));
        verify(employeurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById3() {
        // Arrange
        when(employeurRepository.findById(Mockito.<Long>any())).thenThrow(new EmptyResultDataAccessException(3));

        // Act and Assert
        assertThrows(DatabaseException.class, () -> employeurService.findById(1L));
        verify(employeurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeurService#findById(long)}
     */
    @Test
    void testFindById4() {
        // Arrange
        when(employeurRepository.findById(Mockito.<Long>any())).thenThrow(new DatabaseException("Employeur non trouvé"));

        // Act and Assert
        assertThrows(ServiceException.class, () -> employeurService.findById(1L));
        verify(employeurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployeurService#findAllEmployeur()}
     */
    @Test
    void testFindAllEmployeur() {
        // Arrange, Act and Assert
        assertEquals(2, employeurService.findAllEmployeur().size());
    }

    /**
     * Method under test: {@link EmployeurService#getAllEmployeur()}
     */
    @Test
    void testGetAllEmployeur() {
        // Arrange, Act and Assert
        assertEquals(2, employeurService.getAllEmployeur().size());
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById() {
        // Arrange, Act and Assert
        assertNull(employeurService.getEmployeurById(1L));
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById2() {
        // Arrange and Act
        EmployeurDto actualEmployeurById = employeurService.getEmployeurById(3L);

        // Assert
        assertEquals("4383006589", actualEmployeurById.getPhone());
        assertEquals("Cisco", actualEmployeurById.getEntreprise());
        assertEquals("Lemieux", actualEmployeurById.getPrenom());
        assertEquals("Patrique", actualEmployeurById.getNom());
        assertEquals("lemieux@vaneixus.com", actualEmployeurById.getEmail());
        assertEquals(1L, actualEmployeurById.getProgramme_id());
        assertEquals(3L, actualEmployeurById.getId());
    }

    /**
     * Method under test: {@link EmployeurService#getEmployeurById(Long)}
     */
    @Test
    void testGetEmployeurById3() {
        // Arrange and Act
        EmployeurDto actualEmployeurById = employeurService.getEmployeurById(4L);

        // Assert
        assertEquals("4387996589", actualEmployeurById.getPhone());
        assertEquals("Lacroix", actualEmployeurById.getPrenom());
        assertEquals("Norton", actualEmployeurById.getEntreprise());
        assertEquals("Pierre", actualEmployeurById.getNom());
        assertEquals("lacroix@vaneixus.com", actualEmployeurById.getEmail());
        assertEquals(2L, actualEmployeurById.getProgramme_id());
        assertEquals(4L, actualEmployeurById.getId());
    }
}
