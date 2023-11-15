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
import HomePage from "../pages/shared/HomePage";
import ViewPDFModal from "../components/common/Employer/offer/ViewPDFModal";

const InternshipManagerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['internshipmanager']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route path="home" element={<HomePage/>}>
                        <Route index path="offers" element={<InternshipManagerOffersPage/>}/>
                        <Route path="offers/:id" element={<InternshipManagerOfferPage/>}/>
                        <Route path="studentCvReview" element={<EvaluerCV/>}/>
                        <Route path="internshipsagreement" element={<InternshipManagerInternshipsAgreementPage/>}/>
                        <Route path="internshipagreement/:id" element={<InternshipAgreementPage/>}>
                            <Route path=":fileName" element={<ViewPDFModal/>}/>
                        </Route>
                    </Route>
                </Route>
            </Routes>
        </ProtectedRoute>
)
    ;
};

export default InternshipManagerRouter;
