import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import HomePage from "../pages/HomePage";
import EtudiantStagePage from "../pages/EtudiantStagePage";
import GSOffersPage from "../pages/internshipManager/Offers/GSOffersPage";
import Layout from "../components/layout/Layout";
import EmployeurHomePage from "../pages/EmployeurHomePage";
import EmployeurOffer from "../components/common/EmployeurOffer";
import InternshipOfferForm from "../components/common/InternshipOfferForm";
import ErrorPage from "../pages/ErrorPage";
import EtudiantStage from "../components/common/EtudiantStage";
import TeleversementCV from "../pages/TeleversementCV";

const StudentRoutes: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['STUDENT']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                        <Route path="offers" element={<EtudiantStage/>}/>
                        <Route path="cv" element={<TeleversementCV/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </ProtectedRoute>
    );
};

export default StudentRoutes;
