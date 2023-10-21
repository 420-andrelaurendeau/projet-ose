import React from 'react';
import {Route, Routes} from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import GSOffersPage from "../pages/GSOffersPage";
import Home from "../components/common/Home";

const InternshipManager: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['internshipManager']}>
            <Routes>
                <Route path="/" element={<Home/>}>
                    <Route index element={<Home/>}/>
                    <Route path="offer" element={<GSOffersPage/>}/>

                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default InternshipManager;
