import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import EtudiantStagePage from "../pages/EtudiantStagePage";
import ErrorPage from "../pages/ErrorPage";
import EtudiantStage from "../components/common/student/StudentStage";
import UploadCV from "../components/common/student/UploadCV";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['student']}>
            <Routes>
                <Route path="/home/" element={<EtudiantStagePage/>}>
                        <Route path="offers" element={<EtudiantStage/>}/>
                        <Route path="cv" element={<UploadCV/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </ProtectedRoute>
    );
};

export default StudentRoutes;
