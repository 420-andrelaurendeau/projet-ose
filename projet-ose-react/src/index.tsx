import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './tailwind.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter} from "react-router-dom";
import TestBackEndConnection from "./components/common/testBackEndConnection";
import ConnectPage from "./pages/ConnectPage";
import './i18n.ts';
import StudentInscriptionPage from "./pages/student/StudentInscriptionPage";
import PageEmployeurInscription from "./pages/PageEmployeurInscription";
import StudentInternshipPage from "./pages/student/StudentInternshipPage";
import UploadCVForm from "./components/common/student/form/UploadCVForm";
import {ToastContextProvider} from "./hooks/context/ToastContext";
import InterviewForm from "./components/common/Employer/InterviewForm";
import EmployeurHomePage from "./pages/EmployeurHomePage";
import EmployeurOffer from "./components/common/Employer/EmployeurOffer";
import CandidatureOffer from "./components/common/Employer/CandidatureOffer";
import StudentAppliedOffers from "./components/common/student/offers/StudentAppliedOffers";
import EtudiantStage from "./components/common/student/StudentInternship";
import InternshipManagerOffersPage from "./pages/internshipManager/InternshipManagerOffersPage";
import ErrorPage from "./pages/ErrorPage";
import AppRouter from "./router/appRoutes";
import {AuthProvider} from "./authentication/AuthContext";
import InternshipOfferForm from "./components/common/internshipManager/form/InternshipOfferForm";


if (window.location.pathname == "/employeur/home" || window.location.pathname == "/employeur/home/") {
    window.location.pathname = "/employeur/home/offre"
}


const portalDiv = document.getElementById('root')!;
const root = ReactDOM.createRoot(portalDiv);
const router = createBrowserRouter([
    {
        path: "/signIn",
        element: <ConnectPage/>
    },
    {
        path: "/signInTemp",
        element: <TestBackEndConnection/>
    },
    {
        path: "/",
        element: <App/>,
    },
    {
        path: "/employeur/home",
        element: <EmployeurHomePage/>,
        children: [
            {
                path: "offre",
                element: <EmployeurOffer/>,
                children: [
                    {
                        path: "candidature",
                        element: <CandidatureOffer/>,
                        children: [
                            {
                                path: "InterviewForm",
                                element: <InterviewForm/>
                            }
                        ]
                    },
                    {
                        path: "contrat",
                        element: <p>Contrat</p>,
                    },
                    {
                        path: "offreAttente",
                        element: <p>Offre en attente</p>,
                    },
                    {
                        path: "nouvelleOffre",
                        element: <InternshipOfferForm/>,
                    }
                ]
            },
        ]
    },
    {
        path: "/etudiant/home",
        element: <StudentInternshipPage/>,
        children: [
            {
                path: "/etudiant/home/offre",
                element: <EtudiantStage/>,
                children: []
            },
            {
                path: "/etudiant/home/offreApplique",
                element: <StudentAppliedOffers/>,
            },
            {
                path: "/etudiant/home/TeleverserCV",
                element: <UploadCVForm/>
            },
        ]
    },

    {
        path: "/gs/home",
        element: <InternshipManagerOffersPage/>,
        children: [
            {
                path: "/gs/home/offre",
                element: <InternshipManagerOffersPage/>,
            },
        ]
    },
    {
        path: "/etudiantInscription",
        element: <StudentInscriptionPage/>
    },
    {
        path: "/employeurInscription",
        element: <PageEmployeurInscription/>
    },
    {
        path: "/etudiantStage",
        element: <StudentInternshipPage/>
    },
    {
        path: "/*",
        element: <ErrorPage/>
    }

])

root.render(
    <React.StrictMode>
        <ToastContextProvider>
            <AppRouter/>
        </ToastContextProvider>
    </React.StrictMode>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();