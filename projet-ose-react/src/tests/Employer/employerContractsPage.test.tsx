import EmployerContractsPage from "../../components/common/Employer/employerContractsPage";
import {getStageByEmployeurId} from "../../api/InternshipManagerAPI";
import {MemoryRouter, useNavigate} from "react-router-dom";
import {getAllSeasons} from "../../api/InterOfferJobAPI";
import {useAuth} from "../../authentication/AuthContext";
import {getUser} from "../../api/UtilisateurAPI";
import {findAllByLabelText, fireEvent, render, screen} from "@testing-library/react";
import React from "react";
import agreement from "../../components/common/internshipManager/internshipsAgreement/agreement";
import {act} from "react-dom/test-utils";
import exp from "constants";

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

jest.mock("../../api/UtilisateurAPI", () => ({
    getUser: jest.fn()
}))

jest.mock("../../authentication/AuthContext", () => ({
    useAuth: jest.fn()
}))

jest.mock("../../api/InterOfferJobAPI", () => ({
    getAllSeasons: jest.fn()
}))

const mockedUsedNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
    ...jest.requireActual('react-router-dom') as any,
    useNavigate: () => mockedUsedNavigate,
}))

jest.mock("../../api/InternshipManagerAPI", () => ({
    getStageByEmployeurId: jest.fn()
}))

const mockUser: any = {
    id: 10,
    nom: "Marc",
    prenom: "Max",
    phone: "4387999889",
    email: "max@gmail.com",
    matricule: "2045888",
    entreprise: null,
    programme_id: 1
}

const mockAgreements: any = {
    "content": [
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
                "endDate": "2023-11-26",
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
                "title": "Stage Réseaux 2",
                "location": "Quebec",
                "description": "En tant que stagiaire en réseau chez Cisco, vous aurez l'opportunité de plonger dans le monde passionnant des réseaux informatiques et d'acquérir une expérience pratique précieuse.",
                "salaryByHour": 20.0,
                "startDate": "2024-10-20",
                "endDate": "2023-11-26",
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
                "startDate": "2023-11-26",
                "endDate": "2023-11-26",
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
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 5,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 3,
    "totalPages": 1,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 3,
    "empty": false
}
describe("Employer Contract Page", () => {
    beforeEach(() => {
        (useAuth as jest.Mock).mockResolvedValue({userEmail: "email"});
        (getUser as jest.Mock).mockResolvedValue(mockUser);
        (getAllSeasons as jest.Mock).mockResolvedValue(["Automne2024", "Automne2023"]);
        (getStageByEmployeurId as jest.Mock).mockResolvedValue(mockAgreements)
    })


    test("Renders all items correctly", async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/internshipagreement']}>
                <EmployerContractsPage/>
            </MemoryRouter>
        )
        for (const agreement1 of mockAgreements.content) {
            const html = await screen.findByText(agreement1.internOfferDto.title)
            expect(html).toBeInTheDocument()
        }
    })
})