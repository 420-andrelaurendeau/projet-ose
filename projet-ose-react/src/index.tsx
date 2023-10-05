import React from 'react';
import ReactDOM from 'react-dom/client';

import './index.css';

import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, Navigate, RouterProvider} from "react-router-dom";

import './i18n.ts';

import ErrorPage from "./pages/ErrorPage";
import TestBackEndConnection from "./components/common/testBackEndConnection";

import LoginPage from "./pages/LoginPage";
import StudentRegistrationPage from "./pages/student/StudentRegistrationPage";
import EmployerRegistrationPage from "./pages/employer/EmployerRegistrationPage";
import StudentInternshipOfferPage from "./pages/student/InternshipOfferPage";
import ManagementInternshipOfferPage from './pages/management/offers/InternshipOfferPage';
import AssessCvPage from "./pages/management/students/AssessCv";



const portalDiv = document.getElementById('root')!;
const root = ReactDOM.createRoot(portalDiv);
const router = createBrowserRouter([
    {
        path: "/",
        element: <Navigate to={"/signIn"}/>,
    },
    {
        path: "/signIn",
        element: <LoginPage/>
    },
    {
        path: "/signInTemp",
        element: <TestBackEndConnection/>
    },
    {
        path: "/home",
        element: <ErrorPage/>
    },
    {
        path: "/etudiant/inscription",
        element: <StudentRegistrationPage/>
    },
    {
        path: "/etudiant/offre",
        element: <StudentInternshipOfferPage/>
    },
    {
        path: "/employeur/inscription",
        element: <EmployerRegistrationPage/>
    },
    {
        path: "/gestion/offers",
        element: <ManagementInternshipOfferPage/>
    },
    {
        path: "/gestion/etudiants/cv",
        element: <AssessCvPage/>
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