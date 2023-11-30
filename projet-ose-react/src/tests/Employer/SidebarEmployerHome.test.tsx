import React from 'react';
import {render, screen, fireEvent, act} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter, Route, Routes} from 'react-router-dom';
import SidebarEmployeurHome from "../../components/common/Employer/SidebarEmployeurHome";
import {useAuth} from "../../authentication/AuthContext";
import {useProps} from "../../pages/employer/EmployeurHomePage";

jest.mock("../../pages/employer/EmployeurHomePage", () => {
    return {
        useProps: jest.fn()
    }
})
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

describe('SidebarEmployeurHome Component', () => {
    beforeEach(() => {
        const userRole: string = "employer";
        (useAuth as jest.Mock).mockImplementation(() => {
            return {
                userRole: userRole
            }
        })
    })
    test('renders without errors', () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <SidebarEmployeurHome setIsOpen={() => {
                }} user={{prenom: 'John', nom: 'Doe'}} onOpenProfil={() => {
                }}/>
            </MemoryRouter>
        );
        expect(screen.getByText('John Doe')).toBeInTheDocument();

        const navOffer = screen.getByLabelText("nav-to-offers")
        const navNewOffer = screen.getByLabelText("nav-to-newOffer")

        expect(navOffer).toBeInTheDocument()
        expect(navNewOffer).toBeInTheDocument()

    });

    test('navigates to employeur home offers', () => {
        const userRole: string = "employer";
        (useAuth as jest.Mock).mockImplementation(() => {
            return {
                userRole: userRole
            }
        })
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <Routes>
                    <Route path={"/employer/home/offers"}
                           element={<SidebarEmployeurHome setIsOpen={() => {
                           }} user={{prenom: 'John', nom: 'Doe'}} onOpenProfil={() => {
                           }}/>}>
                    </Route>
                </Routes>


            </MemoryRouter>
        );


        const navContract = screen.getByLabelText("nav-to-offers")

        act(() => {
            fireEvent.click(navContract);
        })

        expect(screen.getByText("John Doe")).toBeInTheDocument()
    });



    test('navigates to employeur home newOffer', () => {
        const userRole: string = "employer";
        (useAuth as jest.Mock).mockImplementation(() => {
            return {
                userRole: userRole
            }
        })
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <Routes>
                    <Route path="/employer/home/newOffer"
                           element={
                               <div aria-label={"nav-success"}>Offers Component</div>
                           }>

                    </Route>
                    <Route path={"/employer/home/offers"}
                           element={<SidebarEmployeurHome setIsOpen={() => {
                           }} user={{prenom: 'John', nom: 'Doe'}} onOpenProfil={() => {
                           }}/>}>
                    </Route>
                </Routes>


            </MemoryRouter>
        );


        const navNewOffer = screen.getByLabelText("nav-to-newOffer")

        act(() => {
            fireEvent.click(navNewOffer);
        })

        expect(screen.getByLabelText("nav-success")).toBeInTheDocument()

    });
});