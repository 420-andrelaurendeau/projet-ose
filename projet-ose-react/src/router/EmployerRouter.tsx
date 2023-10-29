import React from "react";
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "./ProtectedRoute";
import Layout from "../components/layout/Layout";
import EtudiantStagePage from "../pages/EtudiantStagePage";
import GSOffersPage from "../pages/internshipManager/Offers/GSOffersPage";
import GSOfferPage from "../pages/internshipManager/Offer/GSOfferPage";
import EmployeurHomePage from "../pages/EmployeurHomePage";
import EmployeurOffer from "../components/common/Employer/EmployeurOffer";
import ErrorPage from "../pages/ErrorPage";
import InternshipOfferForm from "../components/common/internshipManager/InternshipOfferForm";

const EmployerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['employer']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index path="home" element={<EmployeurHomePage/>}/>
                    <Route path="home" element={<EmployeurHomePage/>}>
                        <Route index path="offers" element={<EmployeurOffer/>}/>
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