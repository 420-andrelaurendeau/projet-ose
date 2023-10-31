import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import StudentInternshipPage from "../pages/student/StudentInternshipPage";
import ErrorPage from "../pages/ErrorPage";
import StudentInternship from "../components/common/student/StudentInternship";
import UploadCVForm from "../components/common/student/form/UploadCVForm";
import StudentAppliedOffers from "../components/common/student/offers/StudentAppliedOffers";
import StudentInterviewPage from "../pages/student/StudentInterviewPage";
import StudentDecisionStagePage from "../pages/student/StudentDecisionStagePage";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['student']}>
            <Routes>
                <Route path="/home/" element={<StudentInternshipPage/>}>
                        <Route path="offers" element={<StudentInternship/>}/>
                        <Route path="appliedOffers" element={<StudentAppliedOffers/>}/>
                        <Route path="cv" element={<UploadCVForm/>}/>
                        <Route path="interview" element={<StudentInterviewPage/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                        <Route path="stage" element={<StudentDecisionStagePage />}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </ProtectedRoute>
    );
};

export default StudentRoutes;
