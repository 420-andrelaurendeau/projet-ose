import React from 'react';
import {Route, Navigate, RouteProps, useNavigate} from 'react-router-dom';
import {useAuth} from "../authentication/AuthContext";


interface ProtectedRouteProps {
    children?: React.ReactNode;
    requiredRoles: string[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children, requiredRoles }) => {
    const { isAuthenticated, userRole } = useAuth();
    const navigate = useNavigate();


    if (!isAuthenticated || !requiredRoles.includes(userRole!)) {
        navigate('/login');
        return null;
    }


    return <>{children}</>;
};

export default ProtectedRoute;


