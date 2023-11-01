import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './tailwind.css';
import reportWebVitals from './reportWebVitals';
import './i18n.ts';
import {ToastContextProvider} from "./hooks/context/ToastContext";
import AppRouter from "./router/appRoutes";



if (window.location.pathname == "/employeur/home" || window.location.pathname == "/employeur/home/") {
    window.location.pathname = "/employeur/home/offre"
}


const portalDiv = document.getElementById('root')!;
const root = ReactDOM.createRoot(portalDiv);
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