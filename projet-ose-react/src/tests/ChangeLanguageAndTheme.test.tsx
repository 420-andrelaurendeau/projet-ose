import {fireEvent, render, screen} from '@testing-library/react';
import Header from "../Header";
import {act} from "react-dom/test-utils";
import {Callback} from "i18next";
import { TFunction } from 'i18next';
import '../index.css';


const user = {
    email: "lacroix@gmail.com",
    entreprise: "poo",
    id: 4,
    nom: "Pierre",
    phone: "4387996589",
    prenom: "Lacroix",
    programme_id: 1
}

const useMock : any = [(k: any) => k, {}];
useMock.t = (k: any) => k;
useMock.i18n = {
    language: 'en-US',
    getResource: (lng: string, ns: string, key: string) => {
        if (lng === 'en') {
            return {
                Header: {
                    profilMenu: {
                        active: {
                            name: 'active',
                            text: "Active"
                        },
                        inactive: {
                            name: 'inactive',
                            text: "Inactive"
                        },
                        changeTheme: {
                            name: 'changeTheme',
                            text: "Change theme"
                        },
                        changeLanguage: {
                            name: 'changeLanguage',
                            text: "Change language"
                        }
                    }
                },
                programs: {
                    techniqueInformatique: {
                        id: 1,
                        name: 'techniqueInformatique',
                        text: 'Computer Science Technology',
                    },
                    techniqueAdministration: {
                        id: 2,
                        name: 'techniqueAdministration',
                        text: 'Administrative Technology',
                    },
                    techniqueLogistique: {
                        id: 3,
                        name: 'techniqueLogistique',
                        text: 'Logistics Technology',
                    },
                    techniqueComptabiliteAndGestion: {
                        id: 4,
                        name: 'techniqueComptabiliteEtGestion',
                        text: 'Accounting and Management Technology',
                    },
                },
            }
        }else {
            return {

                Header: {
                    profilMenu: {
                        active: {
                            name: 'active',
                            text: "Actif"
                        },
                        inactive: {
                            name: 'inactive',
                            text: "Inactif"
                        },
                        changeTheme: {
                            name: 'changeTheme',
                            text: "Changer le thème"
                        },
                        changeLanguage: {
                            name: 'changeLanguage',
                            text: "Changer la langue"
                        }
                    }
                },
                programs: {
                    techniqueInformatique: {
                        id: 1,
                        name: 'techniqueInformatique',
                        text: 'Technique Informatique',
                    },
                    techniqueAdministration: {
                        id: 2,
                        name: 'techniqueAdministration',
                        text: 'Technique Administration',
                    },
                    techniqueLogistique: {
                        id: 3,
                        name: 'techniqueLogistique',
                        text: 'Technique Logistique',
                    },
                    techniqueComptabiliteAndGestion: {
                        id: 4,
                        name: 'techniqueComptabiliteEtGestion',
                        text: 'Technique Comptabilité et Gestion',
                    },
                },
            }
        }
    },
    changeLanguage: (lng?: string | undefined, callback?: Callback | undefined):  Promise<TFunction> => {
        useMock.i18n.language = lng;
        return new Promise<TFunction>((resolve, reject) => {
            resolve(useMock.t);
        });
    }
};

jest.mock('react-router-dom', () => ({
    ...jest.requireActual('react-router-dom'),
    useLocation: () => ({
        state: user,
    }),
}));

jest.mock('react-i18next', () => ({
    useTranslation: () => useMock,
}));

global.ResizeObserver = class ResizeObserver {
    observe() {
        // do nothing
    }
    unobserve() {
        // do nothing
    }
    disconnect() {
        // do nothing
    }
};

describe('The profile menu', () => {


    it('should change the language', async () => {
        render(<Header/>);
        const profileButton = screen.getByTestId('profil-button');
        await act(async () => {
            fireEvent.click(profileButton);
        });
        const programName = screen.getByTestId('programme-text');
        expect(programName).toBeInTheDocument();
        expect(programName).toHaveTextContent('Computer Science Technology');

        const changeLanguageButton = screen.getByTestId('change-language');
        await act(async () => {
            fireEvent.click(changeLanguageButton);
        });
        expect(programName).toHaveTextContent('Technique Informatique');
    });

    it('should change the theme', async () => {
        render(<Header/>);
        const profileButton = screen.getByTestId('profil-button');
        await act(async () => {
            fireEvent.click(profileButton);
        });
        const background = screen.getByTestId('background');
        expect(background).toBeInTheDocument();
        expect(background).toHaveStyle('background-color: rgb(231 231 231');
        const changeThemeButton = screen.getByTestId('change-theme');
        await act(async () => {
            fireEvent.click(changeThemeButton);
        });
        expect(background).toHaveStyle('background-color: rgb(26 28 35');
    });
});