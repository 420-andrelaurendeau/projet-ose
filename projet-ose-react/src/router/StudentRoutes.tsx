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
import StudentOfferDetails from "../components/common/student/offers/StudentOfferDetails";
import StudentContractPage from "../components/common/student/studentContractPage";
import InternshipContractPage from "../pages/internshipManager/InternshipContractPage";
import SignContract from "../components/common/preparedoc/SignContract";
import Notifications from "../components/common/shared/notification/Notifications";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['student']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index path="home" element={<StudentInternshipPage/>}/>
                    <Route path="home" element={<StudentInternshipPage/>}>
                        <Route path="notifications" element={<Notifications/>}/>
                        <Route path="offers" element={<StudentInternship/>}/>
                        <Route path="offers/:id" element={<StudentOfferDetails/>}>
                            <Route path=":fileName" element={<ViewPDFModal/>}/>
                        </Route>
                        <Route path="appliedOffers" element={<StudentAppliedOffers/>}/>
                        <Route path="cv" element={<UploadCVForm/>}>

                        </Route>
                        <Route path="upload" element={<CVStudant/>}/>
                        <Route path="interview" element={<StudentInterviewPage/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                        <Route path="stage" element={<StudentStagePage />}/>
                        <Route path="internshipagreement" element={<StudentContractPage />}/>
                        <Route path="internshipagreement/:id" element={<InternshipContractPage/>}>
                            <Route path=":fileName" element={<ViewPDFModal/>}/>
                        </Route>
                        <Route path="internshipagreement/:id/contract" element={<SignContract/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default StudentRoutes;
