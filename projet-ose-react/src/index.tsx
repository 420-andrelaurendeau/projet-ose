import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './tailwind.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, Navigate, RouterProvider} from "react-router-dom";
import SidebarOptionSwitcher from "./pages/SidebarOptionSwitcher";
import TestBackEndConnection from "./components/common/testBackEndConnection";
import ConnectPage from "./pages/ConnectPage";
import './i18n.ts';
import EtudiantInscriptionPage from "./pages/EtudiantInscriptionPage";
import PageEmployeurInscription from "./pages/PageEmployeurInscription";
import EmployeurHomePage from "./pages/EmployeurHomePage";



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
        element: <Navigate to={"/signIn"}/>,
    },
    {
        path: "/home",
        element: <SidebarOptionSwitcher/>
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
        path: "/homeEmployeur",
        element: <EmployeurHomePage/>,
        children: [
            {
                path: "/homeEmployeur/:option",
                element: <SidebarOptionSwitcher/>,
            },
        ]
    },

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