import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './tailwind.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import EtudiantInscriptionPage from "./pages/EtudiantInscriptionPage";

const portalDiv = document.getElementById('root')!;
const root = ReactDOM.createRoot(portalDiv);
root.render(
  <React.StrictMode>
      <BrowserRouter>
          <Routes>
              <Route path="/" Component={App} />
              <Route path="/etudiantInscription" Component={EtudiantInscriptionPage} />
          </Routes>
      </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
