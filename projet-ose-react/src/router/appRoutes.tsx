import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import StudentRoutes from "./studentRoutes";
import InternshipManagerRouter from "./internshipManagerRouter";
import EmployerRouter from "./EmployerRouter";
import ConnectPage from "../pages/ConnectPage";
import ErrorPage from "../pages/ErrorPage";
import Registration from "../pages/Registration";

const AppRouter: React.FC = () => {
    // @ts-ignore
    return (
        <Router>
            <Routes>
                <Route path="/" element={<ConnectPage/>}/>
                <Route path="/login" element={<ConnectPage />} />
                <Route path="/STUDENT/*" element={<StudentRoutes />} />
                <Route path="/EMPLOYEUR/*" element={<EmployerRouter />} />
                <Route path="/ADMIN/*" element={<InternshipManagerRouter />} />
                <Route path="/*" element={<ErrorPage />} />
                <Route path="/inscription" element={<Registration />}/>
            </Routes>
        </Router>
    );
};

export default AppRouter;
