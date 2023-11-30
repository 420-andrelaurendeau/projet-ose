package com.sap.ose.projetose.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sap.ose.projetose.dto.OfferReviewRequestDto;
import com.sap.ose.projetose.exception.ServiceException;
import com.sap.ose.projetose.modeles.State;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OfferReviewRequestServiceTest {
    @Autowired
    private OfferReviewRequestService offerReviewRequestService;

    /**
     * Method under test: {@link OfferReviewRequestService#saveOfferReviewRequest(OfferReviewRequestDto)}
     */
    @Test
    void testSaveOfferReviewRequest() {
        assertThrows(ServiceException.class, () -> offerReviewRequestService
                .saveOfferReviewRequest(new OfferReviewRequestDto(1L, "Comment", State.ACCEPTED, 1L, 5L)));
        assertThrows(ServiceException.class, () -> offerReviewRequestService.saveOfferReviewRequest(null));
        assertThrows(ServiceException.class, () -> offerReviewRequestService
                .saveOfferReviewRequest(new OfferReviewRequestDto(2L, "Comment", State.ACCEPTED, 1L, 5L)));
    }

    /**
     * Method under test: {@link OfferReviewRequestService#getOfferReviewRequest(Long)}
     */
    @Test
    void testGetOfferReviewRequest() {
        assertThrows(ServiceException.class, () -> offerReviewRequestService.getOfferReviewRequest(1L));
    }

    /**
     * Method under test: {@link OfferReviewRequestService#updateStateOfferReviewRequest(OfferReviewRequestDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateStateOfferReviewRequest() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.sap.ose.projetose.exception.InternshipmanagerNotFoundException: Gestionnaire de stage non trouvé.
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.sap.ose.projetose.service.InternshipmanagerService.findById(InternshipmanagerService.java:60)
        //       at com.sap.ose.projetose.service.InternshipmanagerService$$SpringCGLIB$$0.findById(<generated>)
        //       at com.sap.ose.projetose.service.OfferReviewRequestService.updateStateOfferReviewRequest(OfferReviewRequestService.java:81)
        //       at com.sap.ose.projetose.service.OfferReviewRequestService$$SpringCGLIB$$0.updateStateOfferReviewRequest(<generated>)
        //   See https://diff.blue/R013 to resolve this issue.

        offerReviewRequestService
                .updateStateOfferReviewRequest(new OfferReviewRequestDto(1L, "Comment", State.ACCEPTED, 1L, 1L));
    }
}
