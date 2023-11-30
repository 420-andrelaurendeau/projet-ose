import {getOfferById} from "../../api/InterOfferJobAPI";
import {MemoryRouter, Route, Routes, useParams} from "react-router-dom";
import {render, screen} from "@testing-library/react";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import React from "react";
import EmployerOfferDetails from "../../components/common/Employer/offer/EmployerOfferDetails";
import exp from "constants";

const mockResponse = {
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

jest.mock('react-i18next', () => ({
    useTranslation: () => {
        return {
            t: (str: any) => str,
            i18n: {
                language: "en",
                changeLanguage: () => new Promise(() => {
                }),
                getResource: (lang: string, ns: string, key: string) => {
                    return {
                        programs:{}
                    }
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
jest.mock("../../api/InterOfferJobAPI", () => ({
    getOfferById: jest.fn()
}))
describe("Employer Offer details test", () => {
    beforeEach(() => {
        (getOfferById as jest.Mock).mockResolvedValue(mockResponse)
    })

    test("Should render without errors", async () => {
        render(
            <MemoryRouter initialEntries={['/etudiant/home/offers/1']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/etudiant/home/offers/1"
                        element={
                            <ToastContextProvider>
                                <EmployerOfferDetails/>
                            </ToastContextProvider>
                        }>
                    </Route>
                </Routes>
            </MemoryRouter>
        )
        const offerTitle = await screen.findByText(mockResponse.title)
        const offerLocation = await screen.findByText(mockResponse.location)
        const offerSalary = await screen.findByText("$"+mockResponse.salaryByHour)
        const offerDescription = await screen.findByText(mockResponse.description)
        expect(offerTitle).toBeInTheDocument()
        expect(offerLocation).toBeInTheDocument()
        expect(offerSalary).toBeInTheDocument()
        expect(offerDescription).toBeInTheDocument()

    })
})