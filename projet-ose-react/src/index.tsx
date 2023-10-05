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
import InternOfferPage from "./pages/InternOfferPage";
import GSInternOfferPage from './pages/GSInternOfferPage';
import TeleversementCV from "./pages/TeleversementCV";



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
        path: "/home",
        element: <HomePage/>
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
        path: "/InternOffer",
        element: <InternOfferPage/>
    },
    {
        path: "/GSInternOffer",
        element: <GSInternOfferPage/>
    },
    {
        path: "/TeleverserCV",
        element: <TeleversementCV/>
    }

])

root.render(
    <React.StrictMode>
            <RouterProvider router={router}/>
    </React.StrictMode>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();