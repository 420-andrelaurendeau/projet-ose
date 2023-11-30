import React from 'react';
import {render, screen, fireEvent, act} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter, Route, Routes} from 'react-router-dom';
import SidebarEmployeurHome from "../../components/common/Employer/SidebarEmployeurHome";
import {useAuth} from "../../authentication/AuthContext";
import EmployerStagePage from "../../components/common/Employer/employerStagePage";
import {useProps} from "../../pages/employer/EmployeurHomePage";
import {saveEmployerOpinion} from "../../api/StageAPI";
import exp from "constants";

jest.mock("../../authentication/AuthContext", () => {
    return {
        useAuth: jest.fn()
    }
})
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

jest.mock("../../pages/employer/EmployeurHomePage", () => ({
    useProps: jest.fn()
}))
jest.mock("../../api/StageAPI", () => {
    return {
        saveEmployerOpinion: jest.fn()
    }
})

const mockUser = {
    id: 10,
    nom: "Marc",
    prenom: "Max",
    phone: "4387999889",
    email: "max@gmail.com",
    matricule: "2045888",
    entreprise: null,
    programme_id: 1
}

const mockStageAgreement: any[] = [
    {
        "id": 1,
        "employeur": {
            "id": 3,
            "nom": "Patrique",
            "prenom": "Lemieux",
            "phone": "4383006589",
            "email": "lemieux@gmail.com",
            "entreprise": "Cisco",
            "programme_id": 1
        },
        "etudiantDto": {
            "id": 1,
            "nom": "Marc",
            "prenom": "Max",
            "phone": "4387999889",
            "email": "max@gmail.com",
            "matricule": "2045888",
            "programme_id": 1,
            "cv": [],
            "internships_id": null
        },
        "internOfferDto": {
            "id": 1,
            "title": "Stage Informatique",
            "location": "Laval",
            "description": "En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",
            "salaryByHour": 20.0,
            "startDate": "2023-11-25",
            "endDate": "2023-11-25",
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
            "offerReviewRequestId": 0,
            "state": "PENDING",
            "session": "Automne2023"
        },
        "stateStudent": "ACCEPTED",
        "stateEmployeur": "PENDING",
        "contractId": 0
    },
    {
        "id": 3,
        "employeur": {
            "id": 3,
            "nom": "Patrique",
            "prenom": "Lemieux",
            "phone": "4383006589",
            "email": "lemieux@gmail.com",
            "entreprise": "Cisco",
            "programme_id": 1
        },
        "etudiantDto": {
            "id": 2,
            "nom": "Loic",
            "prenom": "Lac",
            "phone": "4352996589",
            "email": "Lac@gmail.com",
            "matricule": "2045898",
            "programme_id": 1,
            "cv": [],
            "internships_id": null
        },
        "internOfferDto": {
            "id": 3,
            "title": "Stage Réseaux 1",
            "location": "Quebec",
            "description": "En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",
            "salaryByHour": 20.0,
            "startDate": "2024-10-20",
            "endDate": "2023-11-25",
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
            "offerReviewRequestId": 0,
            "state": "PENDING",
            "session": "Automne2024"
        },
        "stateStudent": "DECLINED",
        "stateEmployeur": "ACCEPTED",
        "contractId": 0
    },
    {
        "id": 4,
        "employeur": {
            "id": 3,
            "nom": "Patrique",
            "prenom": "Lemieux",
            "phone": "4383006589",
            "email": "lemieux@gmail.com",
            "entreprise": "Cisco",
            "programme_id": 1
        },
        "etudiantDto": {
            "id": 2,
            "nom": "Loic",
            "prenom": "Lac",
            "phone": "4352996589",
            "email": "Lac@gmail.com",
            "matricule": "2045898",
            "programme_id": 1,
            "cv": [],
            "internships_id": null
        },
        "internOfferDto": {
            "id": 3,
            "title": "Stage Réseaux 2",
            "location": "Quebec",
            "description": "En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",
            "salaryByHour": 20.0,
            "startDate": "2024-10-20",
            "endDate": "2023-11-25",
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
            "offerReviewRequestId": 0,
            "state": "PENDING",
            "session": "Automne2024"
        },
        "stateStudent": "PENDING",
        "stateEmployeur": "DECLINED",
        "contractId": 0
    }
]

const mockProps = {
    user: mockUser,
    stageAgreement: mockStageAgreement,
    "sortAgreementField": "",
    "sortAgreementDirection": "",
    "totalPageAgreement": 1,
    "numberElementAgreementByPage": 5,
    "pageAgreement": 0,
    "seasons": [
        "Automne2024",
        "Automne2023"
    ],
    selectedOption: "",
    setOnChangeAgreement: jest.fn(),
    handleOptionChange: jest.fn(),
    handleChangeDirection: jest.fn(),
    onPageChangeAgreement: jest.fn(),
    setAgreementSortDirection: (input: string) => {
        mockProps.sortAgreementDirection = input;
    },
    setAgreementSortField: (input: string) => {
        mockProps.sortAgreementField = input;
    },
    handleChangeNumberElementAgreement: jest.fn(),
}

describe("Employeur Stage Page", () => {
    beforeEach(() => {
        (useProps as jest.Mock).mockReturnValue(mockProps);
        (saveEmployerOpinion as jest.Mock).mockResolvedValue({data:"success"})
    })
    test("Renders without errors", async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/stage']}>
                <EmployerStagePage/>
            </MemoryRouter>
        )
        const agreement1 = await screen.findByText(mockStageAgreement[0].internOfferDto.title)
        const agreement2 = await screen.findByText(mockStageAgreement[1].internOfferDto.title)
        const agreement3 = await screen.findByText(mockStageAgreement[2].internOfferDto.title)
        expect(agreement1).toBeInTheDocument()
        expect(agreement2).toBeInTheDocument()
        expect(agreement3).toBeInTheDocument()
    })
    test("Should be able to accept stage", async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/stage']}>
                <EmployerStagePage/>
            </MemoryRouter>
        )
        const acceptButton = await screen.findByLabelText("accept-button")
        expect(acceptButton).toBeInTheDocument()

        act(() => {
                fireEvent.click(acceptButton)
            }
        )

        expect(saveEmployerOpinion).toHaveBeenCalledWith(mockStageAgreement[0].internOfferDto.id, "ACCEPTED")
    })

    test("Should be able to refuse stage", async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/stage']}>
                <EmployerStagePage/>
            </MemoryRouter>
        )
        const refuseButton = await screen.findByLabelText("refuse-button")
        expect(refuseButton).toBeInTheDocument()

        act(() => {
                fireEvent.click(refuseButton)
            }
        )

        expect(saveEmployerOpinion).toHaveBeenCalledWith(mockStageAgreement[0].internOfferDto.id, "DECLINED")
    })

})