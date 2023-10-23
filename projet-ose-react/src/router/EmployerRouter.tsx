import React from "react";
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "./ProtectedRoute";
import Layout from "../components/layout/Layout";
import EtudiantStagePage from "../pages/EtudiantStagePage";
import GSOffersPage from "../pages/internshipManager/Offers/GSOffersPage";
import GSOfferPage from "../pages/internshipManager/Offer/GSOfferPage";
import EmployeurHomePage from "../pages/EmployeurHomePage";
import EmployeurOffer from "../components/common/EmployeurOffer";

const EmployerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['employer']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<Layout/>}/>
                    <Route path="home" element={<EmployeurHomePage/>}>
                        <Route path="offre" element={<EmployeurOffer/>}/>
                        <Route path="" element={<GSOfferPage/>}/>
                    </Route>
                    <Route path="/offer/:id" element={<GSOfferPage/>} />
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default EmployerRouter;