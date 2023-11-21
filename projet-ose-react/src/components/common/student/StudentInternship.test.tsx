import {render, screen, fireEvent, act} from "@testing-library/react";
import StudentInternship from "./StudentInternship";
import {saveStudentInternshipOffer} from "../../../api/intershipCandidatesAPI";
import {useProps} from "../../../pages/student/StudentInternshipPage";
import {getUser} from "../../../api/UtilisateurAPI";
import {allStudentInternshipOffers, getStudentAppliedOffers} from "../../../api/InterOfferJobAPI";
import React, {useTransition} from "react";
import {MemoryRouter, Route, Routes} from "react-router-dom";
import SidebarEtudiant from "./SidebarEtudiant";

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

jest.mock("../../../pages/student/StudentInternshipPage", () => ({
    useProps: jest.fn()
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

interface user {
    id: number,
    nom: string,
    prenom: string,
    phone: string,
    email: string,
    matricule: string,
    entreprise: string | null,
    programme_id: number
}

interface appliedOffer {
    appliedOffer: {
        id: number,
        title: string,
        location: string,
        description: string,
        salaryByHour: number,
        startDate: string,
        endDate: string,
        internshipCandidates: null,
        programmeId: number,
        file: {
            id: number,
            content: string,
            fileName: string,
            isAccepted: string
        },
        employeurId: number,
        programmeNom: string,
        employeurPrenom: string,
        employeurNom: string,
        employeurEntreprise: string,
        offerReviewRequestId: number,
        state: string,
        session: string
    }
}

interface offer {
    id: number,
    title: string,
    location: string,
    description: string,
    salaryByHour: number,
    startDate: string,
    endDate: string,
    internshipCandidates: number[],
    programmeId: number,
    file: {
        id: number,
        content: string,
        fileName: string,
        isAccepted: string
    },
    employeurId: number,
    programmeNom: string,
    employeurPrenom: string,
    employeurNom: string,
    employeurEntreprise: string,
    offerReviewRequestId: number,
    state: string,
    session: string
}

describe("StudentInternship Component", () => {
    const mockUser: user = {
        id: 1,
        nom: "Marc",
        prenom: "Max",
        phone: "4387999889",
        email: "max@gmail.com",
        matricule: "2045888",
        entreprise: null,
        programme_id: 1
    }

    const mockOffers: offer[] = [
        {
            "id": 1,
            "title": "Stage Informatique",
            "location": "Laval",
            "description": "En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",
            "salaryByHour": 20,
            "startDate": "2023-11-20",
            "endDate": "2023-11-20",
            "internshipCandidates": [
                1
            ],
            "programmeId": 1,
            "file": {
                "id": 1,
                "content": "aGVsbG8=",
                "fileName": "Test",
                "isAccepted": "PENDING"
            },
            "employeurId": 3,
            "programmeNom": "Techniques de l'informatique",
            "employeurPrenom": "Lemieux",
            "employeurNom": "Patrique",
            "employeurEntreprise": "Cisco",
            "offerReviewRequestId": 3,
            "state": "ACCEPTED",
            "session": "Automne2023"
        },
        {
            "id": 2,
            "title": "Stage Securité",
            "location": "Montreal",
            "description": "En tant que stagiaire en sécurité informatique chez Norton, vous aurez l'opportunité de plonger dans le monde dynamique de la sécurité des systèmes d'information.",
            "salaryByHour": 20,
            "startDate": "2023-11-20",
            "endDate": "2023-11-20",
            "internshipCandidates": [],
            "programmeId": 1,
            "file": {
                "id": 2,
                "content": "aGVsbG8=",
                "fileName": "Test",
                "isAccepted": "PENDING"
            },
            "employeurId": 4,
            "programmeNom": "Techniques de l'informatique",
            "employeurPrenom": "Lacroix",
            "employeurNom": "Pierre",
            "employeurEntreprise": "Norton",
            "offerReviewRequestId": 2,
            "state": "ACCEPTED",
            "session": "Automne2023"
        },
        {
            "id": 3,
            "title": "Stage Réseaux",
            "location": "Quebec",
            "description": "En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",
            "salaryByHour": 20,
            "startDate": "2024-10-20",
            "endDate": "2023-11-20",
            "internshipCandidates": [],
            "programmeId": 1,
            "file": {
                "id": 3,
                "content": "aGVsbG8=",
                "fileName": "Test",
                "isAccepted": "PENDING"
            },
            "employeurId": 3,
            "programmeNom": "Techniques de l'informatique",
            "employeurPrenom": "Lemieux",
            "employeurNom": "Patrique",
            "employeurEntreprise": "Cisco",
            "offerReviewRequestId": 1,
            "state": "ACCEPTED",
            "session": "Automne2024"
        }
    ];

    const mockAppliedOffers: appliedOffer[] = [
        {
            appliedOffer:
                {
                    id: 1,
                    title: "Stage Informatique",
                    location: "Laval",
                    description: "En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",
                    salaryByHour: 20,
                    startDate: "2023-11-20",
                    endDate: "2023-11-20",
                    internshipCandidates: null,
                    programmeId: 1,
                    file: {
                        id: 1,
                        content: "aGVsbG8=",
                        fileName: "Test",
                        isAccepted: "PENDING"
                    },
                    employeurId: 3,
                    programmeNom: "Techniques de l'informatique",
                    employeurPrenom: "Lemieux",
                    employeurNom: "Patrique",
                    employeurEntreprise: "Cisco",
                    offerReviewRequestId: 3,
                    state: "ACCEPTED",
                    session: "Automne2023"
                }
        },
    ];
    const mockProps = {
        user: mockUser,
        appliedOffers: mockAppliedOffers,
        offers: mockOffers,
        "sortField": "id",
        "sortDirection": "asc",
        "totalPages": 1,
        "numberElementByPage": 5,
        "page": 0,
        "seasons": [
            "Automne2024",
            "Automne2023"
        ],
        "selectedOption": "",
        handleChangeOption: jest.fn(),
        handleChangeDirection: jest.fn(),
        onPageChange: jest.fn(),
        setSortDirection: jest.fn(),
        setSortField: jest.fn(),
        setAppliedOffers: jest.fn(),
        handleChangeNumberElement: jest.fn(),
    }


    const mockInternshipOffers = [
        {id: 1, title: "Internship 1"},
        {id: 2, title: "Internship 2"},
    ];
    const mockSaveStudentInterships =
        {internOfferJob: {id: 2, title: "Internship 2"}, files: []};

    beforeEach(() => {
        // Mock getUser response
        (useProps as jest.Mock).mockReturnValue(mockProps);
        (getUser as jest.Mock).mockResolvedValue(mockUser);
        (allStudentInternshipOffers as jest.Mock).mockResolvedValue(mockInternshipOffers);
        (getStudentAppliedOffers as jest.Mock).mockResolvedValue(mockAppliedOffers);
        (saveStudentInternshipOffer as jest.Mock).mockResolvedValue(mockSaveStudentInterships)
    });

    it("renders the StudentInternship component", async () => {
        render(
            await act(async () => {
                return (<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
                    <Routes>
                        <Route
                            path="/etudiant/home/offers"
                            element={<StudentInternship/>}
                        >
                        </Route>
                    </Routes>
                </MemoryRouter>)
            })
        );

        // Assert that the component and its content are correctly rendered
        const titleElement = screen.getAllByLabelText("component-title");
        titleElement.forEach((element) => {
            expect(element).toBeInTheDocument();
        })

        const offerElements = await screen.findAllByLabelText("internship-row");
        expect(offerElements).toHaveLength(mockOffers.length);
    });

    it("handles applying for an internship offer", async () => {
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={<StudentInternship/>}
                >
                </Route>
            </Routes>
        </MemoryRouter>);
        const applyButtons = await screen.findAllByLabelText("apply-button");

        await act(async () => {
            fireEvent.click(applyButtons[1]);

        });

        const offers = screen.getAllByLabelText("apply-button");
        expect(offers[1]).toBeDisabled();


    });


    it("handles loading applied offers and internship offers", async () => {
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={<StudentInternship/>}
                >
                </Route>
            </Routes>
        </MemoryRouter>);

        // Assert that the applied offers and available offers are loaded and displayed correctly
        const appliedOffer1 = await screen.findByText(mockOffers[0].title);
        const appliedOffer2 = await screen.findByText(mockOffers[1].title);

        expect(appliedOffer1).toBeInTheDocument();
        expect(appliedOffer2).toBeInTheDocument();

        const offers = await screen.findAllByLabelText("apply-button");
        expect(offers[0]).toBeDisabled();
    });
});
