import React from "react";
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "./ProtectedRoute";
import Layout from "../components/layout/Layout";
import StudentInternshipPage from "../pages/student/StudentInternshipPage";
import InternshipManagerOffersPage from "../pages/internshipManager/InternshipManagerOffersPage";
import InternshipManagerOfferPage from "../pages/internshipManager/InternshipManagerOfferPage";
import EmployeurHomePage from "../pages/EmployeurHomePage";
import EmployeurOffer from "../components/common/Employer/EmployeurOffer";
import ErrorPage from "../pages/ErrorPage";
import InternshipOfferForm from "../components/common/internshipManager/form/InternshipOfferForm";
import EmployerOfferDetails from "../components/common/Employer/EmployerOfferDetails";

const EmployerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['employer']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index path="home" element={<EmployeurHomePage/>}/>
                    <Route path="home" element={<EmployeurHomePage/>}>
                        <Route index path="offers" element={<EmployeurOffer/>}/>
                        <Route path="offers/:id" element={<EmployerOfferDetails/>} />
                        <Route path="newOffer" element={<InternshipOfferForm/>}/>
                        <Route path="contract" element={<div>Contract</div>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default EmployerRouter;