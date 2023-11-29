import React from 'react';
import {render, screen} from '@testing-library/react';
import SidebarEtudiant from '../../components/common/student/SidebarEtudiant';
import {MemoryRouter, Route, Routes, useLocation} from "react-router-dom";
import {getStudentAppliedOffers} from "../../api/InterOfferJobAPI";

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useLocation: jest.fn()
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

//TODO slighthly more coverage
describe('SidebarEtudiant Component', () => {
    beforeEach(() => {
        (useLocation as jest.Mock).mockImplementation(() => {
            return ({state: {id: 1, prenom: "John", nom: "Doe"}})
        })
    });
    it('renders the SidebarEtudiant component with an active option', () => {
        const {container} = render(
            <MemoryRouter initialEntries={['/student-offers']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/student-offers"
                        element={<SidebarEtudiant user={{id: 1, prenom: "John", nom: "Doe"}}/>}
                    >
                    </Route>
                </Routes>
            </MemoryRouter>);

        // Check if the user's name is displayed
        const userNameElement = screen.getByText('John Doe');
        expect(userNameElement).toBeInTheDocument();

        // Check if the "Stage" option is active
        const stageOption = screen.getByLabelText('internship-option');
        expect(stageOption).toBeInTheDocument()

        // Check if the "Offres Appliquées" option is not active
        const appliedOption = screen.getByLabelText('applied-internship-option');
        expect(appliedOption).toBeInTheDocument()

        // Add more checks for other elements as needed
    });
});