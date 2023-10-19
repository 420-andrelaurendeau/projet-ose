import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './tailwind.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import TestBackEndConnection from "./components/common/testBackEndConnection";
import ConnectPage from "./pages/ConnectPage";
import './i18n.ts';
import EtudiantInscriptionPage from "./pages/EtudiantInscriptionPage";
import PageEmployeurInscription from "./pages/PageEmployeurInscription";
import EtudiantStagePage from "./pages/EtudiantStagePage";
import HomePage from "./pages/HomePage";
import GSInternOfferPage from './pages/GSInternOfferPage';
import TeleversementCV from "./pages/TeleversementCV";
import {ToastContextProvider} from "./hooks/context/ToastContext";
import Placeholder from "./components/common/Placeholder";
import InterviewForm from "./components/common/InterviewForm";
import EmployeurHomePage from "./pages/EmployeurHomePage";
import EmployeurOffer from "./components/common/EmployeurOffer";
import CandidatureOffer from "./components/common/CandidatureOffer";
import InternshipOfferForm from "./components/common/InternshipOfferForm";
import StudentAppliedOffers from "./components/common/StudentAppliedOffers";
import EtudiantStage from "./components/common/EtudiantStage";


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
        path: "/home/:option",
        element: <HomePage/>,
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
        element: <EtudiantStagePage/>,
        children: [
            {
                path: "/etudiant/home/offre",
                element: <EtudiantStage/>,
                children: [
                    {
                        path: "offreApplique",
                        element: <StudentAppliedOffers/>,
                    }
                ]
            },

        ]
    },
    {
        path: "/etudiantInscription",
        element: <EtudiantInscriptionPage/>
    },
    {
        path: "/employeurInscription",
        element: <PageEmployeurInscription/>
    },
    {
        path: "/etudiantStage",
        element: <EtudiantStagePage/>
    },
    {
        path: "/GSInternOffer",
        element: <GSInternOfferPage/>
    },
    {
        path: "/TeleverserCV",
        element: <TeleversementCV/>
    },
    {
        path: "/InterviewForm",
        element: <InterviewForm />
    }


])

root.render(
    <React.StrictMode>
        <ToastContextProvider>
            <RouterProvider router={router}/>
        </ToastContextProvider>
    </React.StrictMode>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();