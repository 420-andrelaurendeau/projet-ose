import {render, screen, fireEvent, act, waitFor} from "@testing-library/react";
import StudentInternship from "../../components/common/student/StudentInternship";
import {saveStudentInternshipOffer} from "../../api/intershipCandidatesAPI";
import {useProps} from "../../pages/student/StudentInternshipPage";
import React, {useTransition} from "react";
import {MemoryRouter, Route, Routes} from "react-router-dom";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import {fetchDefaultCvByStudentId} from "../../api/StudentApi";
import {getStudentAppliedOffers} from "../../api/InterOfferJobAPI";
import mock = jest.mock;

jest.mock("../../api/UtilisateurAPI", () => ({
    getUser: jest.fn(),
}));

jest.mock("../../api/StudentApi", () => ({
    fetchDefaultCvByStudentId: jest.fn(),
}));

jest.mock("../../api/InterOfferJobAPI", () => ({
    allStudentInternshipOffers: jest.fn(),
    saveStudentInternshipOffer: jest.fn(),
    getStudentAppliedOffers: jest.fn(),
}));

jest.mock("../../api/intershipCandidatesAPI", () => ({
    saveStudentInternshipOffer: jest.fn()
}))

jest.mock("../../pages/student/StudentInternshipPage", () => ({
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

const mockUser: user = {
    id: 10,
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

let mockAppliedOffers: appliedOffer[] = [
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
    "sortField": "",
    "sortDirection": "",
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
    setSortDirection: (input: string) => {
        mockProps.sortDirection = input;
    },
    setSortField: (input: string) => {
        mockProps.sortField = input;
    },
    setAppliedOffers: () => {
        mockProps.appliedOffers = mockAppliedOffersResponse
    },
    handleChangeNumberElement: jest.fn(),
}

const mockAppliedOffersResponse: appliedOffer[] = [
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
    {
        appliedOffer:
            {
                id: 2,
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
]

const mockInternshipOffers = [
    {id: 1, title: "Internship 1"},
    {id: 2, title: "Internship 2"},
];
const mockSaveStudentInterships =
    {internOfferJob: {id: 2, title: "Internship 2 not good"}, files: []};


describe("StudentInternship Component", () => {
    beforeEach(() => {

        (fetchDefaultCvByStudentId as jest.Mock).mockResolvedValue({id: 1, content: "aGVsbG8=", fileName: "Test"});
        (useProps as jest.Mock).mockReturnValue(mockProps);
        (saveStudentInternshipOffer as jest.Mock).mockResolvedValue(mockInternshipOffers)
    });
    afterEach(() => {
        mockProps.appliedOffers = mockAppliedOffers
        mockProps.sortField = "";
        mockProps.sortDirection = "";
    })
    it("renders the StudentInternship component", async () => {
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/etudiant/home/offers"
                        element={
                            <ToastContextProvider>
                                <StudentInternship/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );

        // Assert that the component and its content are correctly rendered
        const titleElement = screen.getAllByLabelText("component-title");
        titleElement.forEach((element) => {
            expect(element).toBeInTheDocument();
        })

        const offerElements = await screen.findAllByLabelText("internship-row");
        expect(offerElements).toHaveLength(mockOffers.length);

    });

    it('should give error when CV loads with error and we can close toast', async () => {
        let error = {status: 500};
        (fetchDefaultCvByStudentId as jest.Mock).mockRejectedValue(error);
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/etudiant/home/offers"
                        element={
                            <ToastContextProvider>
                                <StudentInternship/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );
        const toast_message = await screen.findByLabelText("toast-message");
        expect(toast_message).toBeInTheDocument();
        const toast_container = await screen.findByLabelText("toast-container");
        expect(toast_container.className).toContain("bg-red");
        const close_button = await screen.findByLabelText("toast-dismiss-button");
        await act(async () => {
            fireEvent.click(close_button);
        })
        expect(toast_container.className).toContain("animate-slideOutRight");

    });

    it("handles applying for an internship offer", async () => {
        (getStudentAppliedOffers as jest.Mock).mockResolvedValue(mockAppliedOffersResponse);
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={
                        <ToastContextProvider>
                            <StudentInternship/>
                        </ToastContextProvider>
                    }
                >
                </Route>
            </Routes>
        </MemoryRouter>);
        const applyButtons = await screen.findAllByLabelText("apply-button");

        const sort_by_title = await screen.findByLabelText("sort-by-title-button");


        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });

        await act(async () => {
            expect(applyButtons[1]).toBeEnabled();
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
                    element={
                        <ToastContextProvider>
                            <StudentInternship/>
                        </ToastContextProvider>
                    }
                >
                </Route>
            </Routes>
        </MemoryRouter>);

        const offer1 = await screen.findByText(mockOffers[0].title);
        const offer2 = await screen.findByText(mockOffers[1].title);


        expect(offer1).toBeInTheDocument();
        expect(offer2).toBeInTheDocument();

        const offers_buttons = await screen.findAllByLabelText("apply-button");

        const sort_by_title = await screen.findByLabelText("sort-by-title-button");


        //THIS IS USED TO WAIT FOR STATE TO UPDATE LOL

        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });

        expect(offers_buttons[0]).toBeDisabled();
        expect(offers_buttons[1]).toBeEnabled();
        expect(offers_buttons[2]).toBeEnabled();
    });

    it('Test bad CV on apply', async () => {
        (getStudentAppliedOffers as jest.Mock).mockResolvedValue(mockAppliedOffersResponse);
        (fetchDefaultCvByStudentId as jest.Mock).mockResolvedValue(null);
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={
                        <ToastContextProvider>
                            <StudentInternship/>
                        </ToastContextProvider>
                    }
                >
                </Route>
            </Routes>
        </MemoryRouter>);
        const applyButtons = await screen.findAllByLabelText("apply-button");

        const sort_by_title = await screen.findByLabelText("sort-by-title-button");


        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });

        await act(async () => {
            expect(applyButtons[1]).toBeEnabled();
            fireEvent.click(applyButtons[1]);
        });
        const offers = screen.getAllByLabelText("apply-button");
        expect(offers[1]).toBeEnabled();

        const toast_message = await screen.findByLabelText("toast-message");
        expect(toast_message).toBeInTheDocument();
        const toast_container = await screen.findByLabelText("toast-container");
        expect(toast_container.className).toContain("bg-red");
    });
    it("handles error when applying", async () => {
        (getStudentAppliedOffers as jest.Mock).mockResolvedValue(mockAppliedOffersResponse);
        (saveStudentInternshipOffer as jest.Mock).mockRejectedValue({status: 500});
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={
                        <ToastContextProvider>
                            <StudentInternship/>
                        </ToastContextProvider>
                    }
                >
                </Route>
            </Routes>
        </MemoryRouter>);
        const applyButtons = await screen.findAllByLabelText("apply-button");

        const sort_by_title = await screen.findByLabelText("sort-by-title-button");


        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });
        await act(async () => {
            fireEvent.click(sort_by_title);

        });

        await act(async () => {
            expect(applyButtons[1]).toBeEnabled();
            fireEvent.click(applyButtons[1]);
        });
        const offers = screen.getAllByLabelText("apply-button");
        expect(offers[1]).toBeEnabled();
        const toast_message = await screen.findByLabelText("toast-message");
        expect(toast_message).toBeInTheDocument();
        const toast_container = await screen.findByLabelText("toast-container");
        expect(toast_container.className).toContain("bg-red");
    });
    it("Testing that you can click on the sort buttons", async () => {
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={
                        <ToastContextProvider>
                            <StudentInternship/>
                        </ToastContextProvider>
                    }
                >
                </Route>
            </Routes>
        </MemoryRouter>);

        const sort_by_title = await screen.findByLabelText("sort-by-title-button");
        const sort_by_salary = await screen.findByLabelText("sort-by-salary-button");
        const sort_by_start_date = await screen.findByLabelText("sort-by-start-date-button");
        const sort_by_end_date = await screen.findByLabelText("sort-by-end-date-button");

        expect(sort_by_title).toBeInTheDocument();
        expect(sort_by_salary).toBeInTheDocument();
        expect(sort_by_start_date).toBeInTheDocument();
        expect(sort_by_end_date).toBeInTheDocument();

        await act(async () => {
            fireEvent.click(sort_by_title);
        });
        expect(mockProps.sortField).toBe("title");
        await act(async () => {
            fireEvent.click(sort_by_salary);
        });
        expect(mockProps.sortField).toBe("salaryByHour");
        await act(async () => {
            fireEvent.click(sort_by_start_date);
        });
        expect(mockProps.sortField).toBe("startDate");
        await act(async () => {
            fireEvent.click(sort_by_end_date);
        });
        expect(mockProps.sortField).toBe("endDate");

    });

    it("Testing that you can click on the sort buttons", async () => {
        render(<MemoryRouter initialEntries={['/etudiant/home/offers']} initialIndex={0}>
            <Routes>
                <Route
                    path="/etudiant/home/offers"
                    element={
                        <ToastContextProvider>
                            <StudentInternship/>
                        </ToastContextProvider>
                    }
                >
                </Route>
            </Routes>
        </MemoryRouter>);

        const sort_by_title = await screen.findByLabelText("sort-by-title-button");
        const sort_by_salary = await screen.findByLabelText("sort-by-salary-button");
        const sort_by_start_date = await screen.findByLabelText("sort-by-start-date-button");
        const sort_by_end_date = await screen.findByLabelText("sort-by-end-date-button");

        expect(sort_by_title).toBeInTheDocument();
        expect(sort_by_salary).toBeInTheDocument();
        expect(sort_by_start_date).toBeInTheDocument();
        expect(sort_by_end_date).toBeInTheDocument();
        await act(async () => {
            return fireEvent.click(sort_by_title);
        });

        expect(mockProps.sortField).toBe("title");
        expect(mockProps.sortDirection).toBe("asc");

        await act(async () => {
            return fireEvent.click(sort_by_title);
        });
        expect(mockProps.sortField).toBe("title");
        expect(mockProps.sortDirection).toBe("desc");

        await act(async () => {
            return fireEvent.click(sort_by_end_date);
        });
        expect(mockProps.sortField).toBe("endDate");
        expect(mockProps.sortDirection).toBe("asc");

    })
});
