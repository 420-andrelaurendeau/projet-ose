import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import {useAuth} from "../authentication/AuthContext";


interface ProtectedRouteProps {
    element: React.ReactElement;
    path: string;
    requiredRoles: string[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ element, path, requiredRoles }) => {
    const { isAuthenticated, userRole } = useAuth();

    if (!isAuthenticated || !userRole || !requiredRoles.includes(userRole)) {
        return <Navigate to="/login" />;
    }

    return <Route path={path} element={element} />;
};

export default ProtectedRoute;

