import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import EtudiantStagePage from "../pages/EtudiantStagePage";
import ErrorPage from "../pages/ErrorPage";
import EtudiantStage from "../components/common/EtudiantStage";
import TeleversementCV from "../pages/TeleversementCV";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['STUDENT']}>
            <Routes>
                <Route path="/" element={<EtudiantStagePage/>}>
                        <Route path="offers" element={<EtudiantStage/>}/>
                        <Route path="cv" element={<TeleversementCV/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </ProtectedRoute>
    );
};

export default StudentRoutes;
