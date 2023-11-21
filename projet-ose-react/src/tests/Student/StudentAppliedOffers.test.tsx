import React from 'react';
import {render, screen, waitFor, act} from '@testing-library/react';
import StudentAppliedOffers from '../../components/common/student/offers/StudentAppliedOffers';
import {getStudentAppliedOffers} from '../../api/InterOfferJobAPI';
import {MemoryRouter, Route, Routes, useLocation} from 'react-router-dom';

jest.mock('../../api/InterOfferJobAPI', () => ({
    getStudentAppliedOffers: jest.fn(),
}));

function fail(reason:string = "fail was called in a test.") {
    throw new Error(reason);
}

// @ts-ignore
global.fail = fail;

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

//TODO MORE COVERAGE

describe('StudentAppliedOffers Component', () => {
    const mockAppliedOffers = [
        {
            appliedOffer: {
                id: 1,
                title: 'Internship 1',
                location: 'Location 1',
                startDate: new Date('2023-01-01'),
                employeurEntreprise: 'Company 1',
            },
            appliedFiles: [
                {id: 1, fileName: 'File 1.pdf'},
                {id: 2, fileName: 'File 2.pdf'},
            ],
        },
        {
            appliedOffer: {
                id: 2,
                title: 'Internship 2',
                location: 'Location 2',
                startDate: new Date('2023-02-01'),
                employeurEntreprise: 'Company 2',
            },
            appliedFiles: [
                {id: 3, fileName: 'File 3.pdf'},
                {id: 4, fileName: 'File 4.pdf'},
            ],
        },
    ];

    beforeEach(() => {
        (getStudentAppliedOffers as jest.Mock).mockResolvedValue(mockAppliedOffers);
        (useLocation as jest.Mock).mockImplementation(() => {
            return ({state: {id: 1, name: "John Doe"}})
        })
    });

    it('renders the StudentAppliedOffers component and displays applied offers', async () => {
        render(
            <MemoryRouter initialEntries={['/student-offers']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/student-offers"
                        element={<StudentAppliedOffers/>}
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );

        // Wait for the data to be fetched and displayed
        await waitFor(() => {
            expect(screen.getByText('Internship 1')).toBeInTheDocument();
            expect(screen.getByText('Location 1')).toBeInTheDocument();
            expect(screen.getByText('Company 1')).toBeInTheDocument();
            expect(screen.getByText('File 1.pdf')).toBeInTheDocument();
            expect(screen.getByText('Internship 2')).toBeInTheDocument();
            expect(screen.getByText('Location 2')).toBeInTheDocument();
            expect(screen.getByText('Company 2')).toBeInTheDocument();
            expect(screen.getByText('File 2.pdf')).toBeInTheDocument();
            expect(screen.getByText('File 3.pdf')).toBeInTheDocument();
            expect(screen.getByText('File 4.pdf')).toBeInTheDocument();
        });
    });

    it('should throw error', async () => {
        (useLocation as jest.Mock).mockImplementation(() => {
            return ({state: {ids: 1, name: "John Doe"}})
        })
        try {
            await act(() => {
                render(
                    <MemoryRouter initialEntries={['/student-offers']} initialIndex={0}>
                        <Routes>
                            <Route
                                path="/student-offers"
                                element={<StudentAppliedOffers/>}
                            >
                            </Route>
                        </Routes>
                    </MemoryRouter>
                );
            })
            fail("Should have thrown an error")
        } catch (e) {
        }
    });
});
