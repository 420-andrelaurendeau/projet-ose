import ApplicationOffer from "../../components/common/Employer/application/ApplicationOffer";
import {getOfferById} from "../../api/InterOfferJobAPI";
import {getInterOfferCandidates} from "../../api/intershipCandidatesAPI";
import {findByText, render, screen} from "@testing-library/react";
import {MemoryRouter, Route, Routes} from "react-router-dom";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import {useProps} from "../../pages/employer/EmployeurHomePage";
import React from "react";
import {user} from "../Student/StudentInternship.test";
import exp from "constants";

const mockCandidates: any[] = [
    {
        "id": 1,
        "etudiant": {
            "id": 1,
            "nom": "Marc",
            "prenom": "Max",
            "phone": "4387999889",
            "email": "max@gmail.com",
            "matricule": "2045888",
            "programme_id": 1,
            "cv": [],
            "internships_id": [
                1
            ]
        },
        "internOfferJob": {
            "id": 1,
            "title": "Stage Informatique",
            "location": "Laval",
            "description": "En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",
            "salaryByHour": 20.0,
            "startDate": "2023-11-27",
            "endDate": "2023-11-27",
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
        "files": [
            {
                "id": 4,
                "content": "aGVsbG8=",
                "fileName": "Test",
                "isAccepted": "PENDING"
            }
        ],
        "state": "PENDING",
        "date": null
    }
]

const mockOffer = {
    "id": 1,
    "title": "Stage Informatique",
    "location": "Laval",
    "description": "En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",
    "salaryByHour": 20.0,
    "startDate": "2023-11-27",
    "endDate": "2023-11-27",
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

const mockProps = {
    offers: [mockOffer],
    user: mockUser
}

const mockedUsedNavigate = jest.fn();
jest.mock("react-router-dom", () => ({
    ...jest.requireActual('react-router-dom') as any,
    useNavigate: () => mockedUsedNavigate,
}))

jest.mock("../../pages/employer/EmployeurHomePage", () => ({
    useProps: jest.fn()
}))
jest.mock("../../api/intershipCandidatesAPI", () => ({
    getInterOfferCandidates: jest.fn()
}))

jest.mock("../../api/InterOfferJobAPI", () => ({
    getOfferById: jest.fn()
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

describe("Applicaiton offer test", () => {
    beforeEach(() => {
        (getInterOfferCandidates as jest.Mock).mockResolvedValue(mockCandidates);
        (getOfferById as jest.Mock).mockResolvedValue(mockOffer);
        (useProps as jest.Mock).mockResolvedValue(mockProps);
    })
    test("should render without errors", async () => {
        render(
            <ToastContextProvider>
                <ApplicationOffer/>
            </ToastContextProvider>
        )

        const etudiantEmail = await screen.findByText(mockCandidates[0].etudiant.email)
        const etudiantPhone = await screen.findByText(mockCandidates[0].etudiant.phone)
        expect(etudiantEmail).toBeInTheDocument()
        expect(etudiantPhone).toBeInTheDocument()
    })
})