import React from 'react';
import {render, screen, fireEvent, act} from '@testing-library/react';
import '@testing-library/jest-dom';
import {MemoryRouter, Route, Routes} from 'react-router-dom';
import SidebarEmployeurHome from "../../components/common/Employer/SidebarEmployeurHome";
import {useAuth} from "../../authentication/AuthContext";
import exp from "constants";
import {use} from "i18next";

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
    beforeEach(()=>{
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
        const navApplication = screen.getByLabelText("nav-to-application")
        const navPending = screen.getByLabelText("nav-to-pendingOffer")
        const navContract = screen.getByLabelText("nav-to-contract")
        const navNewOffer = screen.getByLabelText("nav-to-newOffer")

        expect(navOffer).toBeInTheDocument()
        expect(navApplication).toBeInTheDocument()
        expect(navContract).toBeInTheDocument()
        expect(navNewOffer).toBeInTheDocument()
        expect(navPending).toBeInTheDocument()


    });

    test('navigates to employeur home application', () => {
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <Routes>
                    <Route path="/employer/home/application"
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

        const navApplication = screen.getByLabelText("nav-to-application")

        act(() => {
            fireEvent.click(navApplication);
        })

        expect(screen.getByLabelText("nav-success")).toBeInTheDocument()

    });
    test('navigates to employeur home contract', () => {
        const userRole: string = "employer";
        (useAuth as jest.Mock).mockImplementation(() => {
            return {
                userRole: userRole
            }
        })
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <Routes>
                    <Route path="/employer/home/contract"
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


        const navContract = screen.getByLabelText("nav-to-contract")

        act(() => {
            fireEvent.click(navContract);
        })

        expect(screen.getByLabelText("nav-success")).toBeInTheDocument()

    });

    test('navigates to employeur home pending offer', () => {
        const userRole: string = "employer";
        (useAuth as jest.Mock).mockImplementation(() => {
            return {
                userRole: userRole
            }
        })
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <Routes>
                    <Route path="/employer/home/pendingOffer"
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


        const navPending = screen.getByLabelText("nav-to-pendingOffer")

        act(() => {
            fireEvent.click(navPending);
        })

        expect(screen.getByLabelText("nav-success")).toBeInTheDocument()

    });

    test('navigates to employeur home contract', () => {
        const userRole: string = "employer";
        (useAuth as jest.Mock).mockImplementation(() => {
            return {
                userRole: userRole
            }
        })
        render(
            <MemoryRouter initialEntries={['/employer/home/offers']}>
                <Routes>
                    <Route path="/employer/home/contract"
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


        const navContract = screen.getByLabelText("nav-to-contract")

        act(() => {
            fireEvent.click(navContract);
        })

        expect(screen.getByLabelText("nav-success")).toBeInTheDocument()

    });

    test('navigates to employeur home contract', () => {
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