import React from 'react';
import {act, findByLabelText, fireEvent, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import {BrowserRouter as Router, MemoryRouter, Route, Routes} from 'react-router-dom';
import StudentOfferDetails from "../../components/common/student/offers/StudentOfferDetails";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import {getOfferById} from "../../api/InterOfferJobAPI";
import {useNavigate} from "react-router-dom";
import exp from "constants";

const mock_offer = {
    "id": 2,
    "title": "Stage Securité",
    "location": "Montreal",
    "description": "En tant que stagiaire en sécurité informatique chez Norton, vous aurez l'opportunité de plonger dans le monde dynamique de la sécurité des systèmes d'information.",
    "salaryByHour": 20.0,
    "startDate": "2023-11-22",
    "endDate": "2023-11-22",
    "internshipCandidates": [],
    "programmeId": 1,
    "file": {
        "id": 2,
        "content": "aGVsbG8=",
        "fileName": "Test",
        "isAccepted": "PENDING"
    },
    "employeurId": 4,
    "programmeNom": "Technique en informatique",
    "employeurPrenom": "Lacroix",
    "employeurNom": "Pierre",
    "employeurEntreprise": "Norton",
    "offerReviewRequestId": 2,
    "state": "ACCEPTED",
    "session": "Automne2023"
}

const mockedUsedNavigate = jest.fn();
jest.mock("react-router-dom", () => ({
    ...jest.requireActual('react-router-dom') as any,
    useNavigate: () => mockedUsedNavigate,
}))
jest.mock("../../api/InterOfferJobAPI", () => {
    return {
        getOfferById: jest.fn()
    }
})

jest.mock('react-i18next', () => ({
    useTranslation: () => {
        return {
            t: (str: any) => str,
            i18n: {
                changeLanguage: () => new Promise(() => {
                }),
                language: "FR",
                getResource: () => {
                    return {
                        programs: {
                            techniqueInformatique: {
                                id: 1,
                                name: 'techniqueInformatique',
                                text: 'Technique en informatique'
                            },
                            techniqueAdministration: {
                                id: 2,
                                name: 'techniqueAdministration',
                                text: 'Technique en administration',
                            },
                            techniqueLogistique: {
                                id: 3,
                                name: 'techniqueLogistique',
                                text: 'Technique en logistique du transport',
                            },
                            techniqueComptabiliteAndGestion: {
                                id: 4,
                                name: 'techniqueComptabiliteEtGestion',
                                text: 'Technique en comptabilité et gestion',
                            },
                            errorFetchOffer: {
                                name: "errorFetchOffer",
                                text: "Erreur lors de la récupération de l'offre",
                            },
                        }
                    }
                }
            }
        }
    }
}))

describe('StudentOfferDetails Component', () => {
    beforeEach(() => {
        (getOfferById as jest.Mock).mockResolvedValue(mock_offer)
    })
    test('renders without errors', async () => {
        render(
            <MemoryRouter initialEntries={['/student/home/offers/2']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/student/home/offers/2"
                        element={
                            <ToastContextProvider>
                                <StudentOfferDetails state={null}/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );

        const title = await screen.findAllByText(mock_offer.title)
        const location = await screen.findByText(mock_offer.location)
        const program = await screen.findByText(mock_offer.programmeNom)
        const salary = await screen.findByText("$" + mock_offer.salaryByHour)
        const description = await screen.findByText(mock_offer.description)
        const file_name = await screen.findByText(mock_offer.file.fileName)

        title.forEach((element) => {
            expect(element).toBeInTheDocument()
        })

        expect(location).toBeInTheDocument()
        expect(program).toBeInTheDocument()
        expect(salary).toBeInTheDocument()
        expect(description).toBeInTheDocument()
        expect(file_name).toBeInTheDocument()
    });

    it('should navigate to home offers', async () => {
        render(
            <MemoryRouter initialEntries={['/student/home/offers/2']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/student/home/offers/2"
                        element={
                            <ToastContextProvider>
                                <StudentOfferDetails state={null}/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );
        const back_button = await screen.findByLabelText("back-button")

        await act(async () => {
            fireEvent.click(back_button)
        })

        expect(mockedUsedNavigate).toHaveBeenCalledWith("/student/home/offers")
    });
});
