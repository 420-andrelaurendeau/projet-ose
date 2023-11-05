import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import InternshipManagerOffersPage from "../pages/internshipManager/InternshipManagerOffersPage";
import Layout from "../components/layout/Layout";
import InternshipManagerOfferPage from "../pages/internshipManager/InternshipManagerOfferPage";
import EvaluerCV from "../pages/management/student/AssessCv";
import InternshipManagerInternshipsAgreementPage
    from "../pages/internshipManager/InternshipManagerInternshipsAgreementPage";
import InternshipAgreementPage from "../pages/internshipManager/InternshipAgreementPage";

const InternshipManagerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['internshipmanager']}>
            <Routes>
                <Route path="/home/" element={<Layout/>}>
                    <Route element={<Layout/>}/>
                    <Route index path="studentCvReview" element={<EvaluerCV/>}/>
                    <Route index path="offers" element={<InternshipManagerOffersPage/>}/>
                    <Route path="offer/:id" element={<InternshipManagerOfferPage/>}/>
                    <Route path="internshipsagreement" element={<InternshipManagerInternshipsAgreementPage/>}/>
                    <Route path="internshipagreement/:id" element={<InternshipAgreementPage/>}/>
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default InternshipManagerRouter;
