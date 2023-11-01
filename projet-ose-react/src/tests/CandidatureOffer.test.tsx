import {fireEvent, render, screen} from '@testing-library/react';
import {Callback, TFunction} from "i18next";
import CandidatureOffer from "../components/common/CandidatureOffer";


const Offres = [{
    description: "ff",
    employeurEntreprise: "popo",
    employeurId: 3,
    employeurNom: "Patrique",
    employeurPrenom: "Lemieux",
    endDate: "2023-10-19",
    file: {
        accepted: true,
        content: "aGVsbG8=",
        fileName: "Test",
        id: 1,
    },
    id: 1,
    internshipCandidates: [1],
    location: "Laval",
    offerReviewRequestId: 1,
    programmeId: 1,
    programmeNom: "Techniques de l'informatique",
    salaryByHour: 0,
    startDate: "2023-10-19",
    state: "ACCEPTED",
    title: "Stage Informatique"
}];

const user = {
    email: "lacroix@gmail.com",
    entreprise: "poo",
    id: 4,
    nom: "Pierre",
    phone: "4387996589",
    prenom: "Lacroix",
    programme_id: 1
};

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




