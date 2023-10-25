import React from "react";
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "./ProtectedRoute";
import Layout from "../components/layout/Layout";
import EtudiantStagePage from "../pages/EtudiantStagePage";
import GSOffersPage from "../pages/internshipManager/Offers/GSOffersPage";
import GSOfferPage from "../pages/internshipManager/Offer/GSOfferPage";
import EmployeurHomePage from "../pages/EmployeurHomePage";
import EmployeurOffer from "../components/common/EmployeurOffer";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import ErrorPage from "../pages/ErrorPage";

const EmployerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['EMPLOYEUR']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route path="home" element={<EmployeurHomePage/>}>
                        <Route path="offers" element={<EmployeurOffer/>}/>
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