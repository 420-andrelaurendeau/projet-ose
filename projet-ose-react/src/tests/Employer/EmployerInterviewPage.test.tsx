import {EmployerInterviewPage} from "../../components/common/Employer/EmployerInterviewPage";
import {render, screen} from "@testing-library/react";
import {MemoryRouter} from "react-router-dom";
import React from "react";
import {fetchInterviewsEmployer} from "../../api/InterviewApi";
import {getUser} from "../../api/UtilisateurAPI";
import {useAuth} from "../../authentication/AuthContext";
import {getAllSeasons} from "../../api/InterOfferJobAPI";

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


jest.mock("../../api/InterviewApi", () => {
    return {
        fetchInterviewsEmployer: jest.fn()
    }
})
jest.mock("../../api/UtilisateurAPI", () => ({

    getUser: jest.fn()
}))

jest.mock("../../authentication/AuthContext", () => ({
    useAuth: jest.fn()
}))

jest.mock("../../api/InterOfferJobAPI", ()=>({
    getAllSeasons:jest.fn()
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

const mockInterviews: any = {
    "content": [
        {
            "id": 1,
            "student": {
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
            "internOffer": {
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
            "date": "2023-11-30T00:00:00.000+00:00",
            "description": "",
            "state": "PENDING"
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
    "totalElements": 1,
    "totalPages": 1,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}


describe("Employer Interview Page", () => {
    beforeEach(() => {
        (getAllSeasons as jest.Mock).mockResolvedValue(["Automne2024", "Automne2023"]);
        (getUser as jest.Mock).mockResolvedValue(mockUser);
        (fetchInterviewsEmployer as jest.Mock).mockResolvedValue(mockInterviews);
        (useAuth as jest.Mock).mockResolvedValue({userEmail: "email"});
    })

    test("renders all items correctly", async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/interview']}>
                <EmployerInterviewPage/>
            </MemoryRouter>
        )

        const interview = await screen.findByText(mockInterviews.content[0].internOffer.title)
        expect(interview).toBeInTheDocument()
    })
})