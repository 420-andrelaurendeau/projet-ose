import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import StudentInternshipPage from "../pages/student/StudentInternshipPage";
import ErrorPage from "../pages/ErrorPage";
import StudentInternship from "../components/common/student/StudentInternship";
import UploadCVForm from "../components/common/student/form/UploadCVForm";
import StudentAppliedOffers from "../components/common/student/offers/StudentAppliedOffers";
import StudentInterviewPage from "../pages/student/StudentInterviewPage";
import StudentStagePage from "../pages/student/StudentStagePage";
import Layout from "../components/layout/Layout";
import CVStudant from "../components/common/student/CVStudant";
import ViewPDFModal from "../components/common/Employer/offer/ViewPDFModal";
import {pdfjs} from "react-pdf/dist/esm";
import StudentContractPage from "../components/common/student/studentContractPage";
import InternshipAgreementPage from "../pages/internshipManager/InternshipAgreementPage";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['student']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index path="home" element={<StudentInternshipPage/>}/>
                    <Route path="home" element={<StudentInternshipPage/>}>
                        <Route path="offers" element={<StudentInternship/>}/>
                        <Route path="appliedOffers" element={<StudentAppliedOffers/>}/>
                        <Route path="cv" element={<UploadCVForm/>}>

                        </Route>
                        <Route path="upload" element={<CVStudant/>}/>
                        <Route path="interview" element={<StudentInterviewPage/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                        <Route path="stage" element={<StudentStagePage />}/>
                        <Route path="contract" element={<StudentContractPage />}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default StudentRoutes;
