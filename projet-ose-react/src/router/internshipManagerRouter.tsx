import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import InternshipManagerOffersPage from "../pages/internshipManager/InternshipManagerOffersPage";
import Layout from "../components/layout/Layout";
import InternshipManagerOfferPage from "../pages/internshipManager/InternshipManagerOfferPage";

const InternshipManager: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['internshipmanager']}>
            <Routes>
                <Route path="/home/" element={<Layout/>}>
                    <Route element={<Layout/>}/>
                    <Route index path="offers" element={<InternshipManagerOffersPage/>}/>
                    <Route path="offer/:id" element={<InternshipManagerOfferPage/>} />
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default InternshipManager;
