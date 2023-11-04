import {render, screen, fireEvent} from "@testing-library/react";
import StudentStage from "./StudentStage";
import {saveStudentInternshipOffer} from "../../../api/intershipCandidatesAPI";
import {getUser} from "../../../api/UtilisateurAPI";
import {allStudentInternshipOffers, getStudentAppliedOffers} from "../../../api/InterOfferJobAPI";
import {useTransition} from "react";
import {act} from "react-dom/test-utils";

jest.mock("../../../api/UtilisateurAPI", () => ({
    getUser: jest.fn(),
}));

jest.mock("../../../api/InterOfferJobAPI", () => ({
    allStudentInternshipOffers: jest.fn(),
    saveStudentInternshipOffer: jest.fn(),
    getStudentAppliedOffers: jest.fn(),
}));

jest.mock("../../../api/intershipCandidatesAPI", () => ({
    saveStudentInternshipOffer: jest.fn()
}))

jest.mock('react-i18next', () => ({
    useTranslation: () => {
        return {
            t: (str: any) => str,
            i18n: {
                language: "en",
                changeLanguage: () => new Promise(() => {
                }),
                getResource: (lang: string, ns: string, key: string) => {
                    return key
                },
            },

        };
    },
    initReactI18next: {
        type: '3rdParty',
        init: () => {
        },
    }
}));

describe("StudentStage Component", () => {
    const mockUser = {id: 1, name: "John Doe"};
    const mockAppliedOffers = [
        {appliedOffer: {id: 1, title: "Internship 1"}},
    ];
    const mockInternshipOffers = [
        {id: 1, title: "Internship 1"},
        {id: 2, title: "Internship 2"},
    ];
    const mockSaveStudentInterships =
        {internOfferJob: {id: 2, title: "Internship 2"}, files: []};

    beforeEach(() => {
        // Mock getUser response
        (getUser as jest.Mock).mockResolvedValue(mockUser);
        // Mock API responses
        (allStudentInternshipOffers as jest.Mock).mockResolvedValue(mockInternshipOffers);
        (getStudentAppliedOffers as jest.Mock).mockResolvedValue(mockAppliedOffers);
        (saveStudentInternshipOffer as jest.Mock).mockResolvedValue(mockSaveStudentInterships)
    });

    it("renders the StudentStage component", async () => {
        render(<StudentStage/>);

        // Assert that the component and its content are correctly rendered
        const titleElement = screen.getByText("formField.EtudiantStage.titre.text");
        expect(titleElement).toBeInTheDocument();
        const offerElements = await screen.findAllByLabelText("stage");
        expect(offerElements).toHaveLength(mockInternshipOffers.length);
    });

    it("handles applying for an internship offer", async () => {
        render(<StudentStage/>);
        const applyButtons = await screen.findAllByLabelText("apply");

        act(() => {
            fireEvent.click(applyButtons[1]);

        })
        //Puisque les button apply ne sont pas encore re rendered mais encore la il vas pas wait pour update de state
        setTimeout(async () => {
            const offers = await screen.findAllByLabelText("apply");
            expect(offers[1]).toBeDisabled();
        }, 1)

    });


    it("handles loading applied offers and internship offers", async () => {
        render(<StudentStage/>);

        // Assert that the applied offers and available offers are loaded and displayed correctly
        const appliedOffer1 = await screen.findByText("Internship 1");
        const appliedOffer2 = await screen.findByText("Internship 2");

        expect(appliedOffer1).toBeInTheDocument();
        expect(appliedOffer2).toBeInTheDocument();

        const offers = await screen.findAllByLabelText("apply");
        expect(offers[0]).toBeDisabled();
    });

    // Add more test cases to cover different scenarios in your component
});
