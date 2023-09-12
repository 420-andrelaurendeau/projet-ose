import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

import reportWebVitals from './reportWebVitals';
import ConnectPage from "./pages/ConnectPage";
import {createBrowserRouter, Navigate, RouterProvider} from "react-router-dom";

const router = createBrowserRouter([
    {
        path: "/main",
        element: <ConnectPage/>,
    },
    {
        path: "/",
        element: <Navigate to='/main'/>,
    },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
