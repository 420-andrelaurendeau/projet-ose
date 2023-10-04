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
import HomePage from "./pages/HomePage";


if(window.location.pathname == "/homeEmployeur" || window.location.pathname == "/homeEmployeur/"){
    window.location.pathname = "/homeEmployeur/offer"
    console.log(1)
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
        path: "/etudiantInscription",
        element: <EtudiantInscriptionPage/>
    },
    {
        path: "/employeurInscription",
        element: <PageEmployeurInscription/>
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