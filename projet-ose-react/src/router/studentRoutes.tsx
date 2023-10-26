import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import Layout from "../components/layout/Layout";
import ErrorPage from "../pages/ErrorPage";
import EtudiantStage from "../components/common/EtudiantStage";
import TeleversementCV from "../pages/TeleversementCV";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['student']}>
            <Routes>
                <Route path="/home/" element={<Layout/>}>
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
