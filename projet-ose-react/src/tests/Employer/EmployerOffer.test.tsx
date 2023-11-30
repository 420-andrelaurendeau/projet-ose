import {useProps} from "../../pages/employer/EmployeurHomePage";
import {MemoryRouter, useNavigate} from "react-router-dom";
import {fireEvent, render, screen} from "@testing-library/react";
import React from "react";
import EmployeurOffer from "../../components/common/Employer/offer/EmployeurOffer";
import {offer, user} from "../Student/StudentInternship.test";
import {act} from "react-dom/test-utils";

const mockedUsedNavigate = jest.fn();
jest.mock("react-router-dom", () => ({
    ...jest.requireActual('react-router-dom') as any,
    useNavigate: () => mockedUsedNavigate,
}))

jest.mock("../../pages/employer/EmployeurHomePage", () => ({
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
    user: mockUser,
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
    handleChangeNumberElement: jest.fn(),
}
describe("Employer offer tests", () => {
    beforeEach(() => {
        (useProps as jest.Mock).mockReturnValue(mockProps)
    })
    test("renders everything without erros", async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <EmployeurOffer/>
            </MemoryRouter>
        )
        for (const offer of mockOffers) {
            expect(await screen.findByText(offer.title)).toBeInTheDocument()
        }
    })
    test("should be able to navigate to offer details",async () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <EmployeurOffer/>
            </MemoryRouter>
        )
        const navButton = await screen.findAllByLabelText("nav-button")
        let id = 1;
        for (const button of navButton) {
            act(() => {
                fireEvent.click(button)
                expect(mockedUsedNavigate).toHaveBeenCalledWith(`/employer/home/offers/${id}`)
                id++
            })
        }
    })
})