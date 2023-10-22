import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import GSOffersPage from "../pages/internshipManager/Offers/GSOffersPage";
import Layout from "../components/layout/Layout";
import GSOfferPage from "../pages/internshipManager/Offer/GSOfferPage";

const InternshipManager: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['internshipManager']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<Layout/>}/>
                    <Route path="offers" element={<GSOffersPage/>}/>
                    <Route path="/offer/:id" element={<GSOfferPage/>} />
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default InternshipManager;
