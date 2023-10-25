import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import StudentRoutes from "./studentRoutes";
import InternshipManagerRouter from "./internshipManagerRouter";
import EmployerRouter from "./EmployerRouter";
import ConnectPage from "../pages/ConnectPage";
import ErrorPage from "../pages/ErrorPage";

const AppRouter: React.FC = () => {
    return (
        <Router>
            <Routes>
                <Route path="/student/*" element={<StudentRoutes />} />
                <Route path="/EMPLOYEUR/*" element={<EmployerRouter/>}/>
                <Route path="/ETUDIANT/*" element={<InternshipManagerRouter/>}/>
                <Route path="/login" element={<ConnectPage/>}/>
                <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </Router>
    );
};

export default AppRouter;
