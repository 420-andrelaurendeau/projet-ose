import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import StudentRoutes from "./studentRoutes";
import InternshipManagerRouter from "./internshipManagerRouter";
import EmployerRouter from "./EmployerRouter";
import ConnectPage from "../pages/ConnectPage";

const AppRouter: React.FC = () => {
    return (
        <Router>
            <Routes>
                <Route path="/student/*" element={<StudentRoutes />} />
                <Route path="/employer/*" element={<EmployerRouter/>}/>
                <Route path="/ETUDIANT/*" element={<InternshipManagerRouter/>}/>


                <Route path="/login" element={<ConnectPage></ConnectPage>}/>
                <Route path="«" element={<></>}/>
            </Routes>
        </Router>
    );
};

export default AppRouter;
