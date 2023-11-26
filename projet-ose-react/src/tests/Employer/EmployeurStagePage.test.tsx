import React from 'react';
import {render, screen, fireEvent, act} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter, Route, Routes} from 'react-router-dom';
import SidebarEmployeurHome from "../../components/common/Employer/SidebarEmployeurHome";
import {useAuth} from "../../authentication/AuthContext";
import EmployerStagePage from "../../components/common/Employer/employerStagePage";
import {useProps} from "../../pages/employer/EmployeurHomePage";

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

const mockStageAgreement:any[] = [
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
            "title": "Stage Réseaux",
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
            "title": "Stage Réseaux",
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
    "sortField": "",
    "sortDirection": "",
    "totalPages": 1,
    "numberElementByPage": 5,
    "pageAgreement": 0,
    "seasons": [
        "Automne2024",
        "Automne2023"
    ],
    selectedOption: "",
    handleChangeOption: jest.fn(),
    handleChangeDirection: jest.fn(),
    onPageChange: jest.fn(),
    setSortDirection: (input: string) => {
        mockProps.sortDirection = input;
    },
    setSortField: (input: string) => {
        mockProps.sortField = input;
    },
    handleChangeNumberElement: jest.fn(),
}

describe("Employeur Stage Page", () => {
    beforeEach(()=>{
        (useProps as jest.Mock).mockReturnValue(mockProps);
    })
    test("Renders without errors", () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/stage']}>
                <EmployerStagePage/>
            </MemoryRouter>
        )
    })
})