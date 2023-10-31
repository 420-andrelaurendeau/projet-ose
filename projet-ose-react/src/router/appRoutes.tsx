import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import StudentRoutes from "./studentRoutes";
import InternshipManagerRouter from "./InternshipManagerRouter";
import EmployerRouter from "./EmployerRouter";
import ConnectPage from "../pages/ConnectPage";
import ErrorPage from "../pages/ErrorPage";
import {AuthProvider} from "../authentication/AuthContext";

const AppRouter: React.FC = () => {
    return (
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/"  element={<ConnectPage/>}></Route>
                    <Route path="*" element={<ErrorPage/>}/>
                    <Route path="/login" index element={<ConnectPage/>}/>
                    <Route path="/student/*" element={<StudentRoutes />} />
                    <Route path="/employer/*" element={<EmployerRouter/>}/>
                    <Route path="/internshipmanager/*" element={<InternshipManagerRouter/>}/>
                </Routes>
            </AuthProvider>
        </Router>
    );
};

export default AppRouter;