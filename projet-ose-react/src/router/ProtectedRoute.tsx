import React, {useEffect} from 'react';
import {Route, Navigate, RouteProps, useNavigate} from 'react-router-dom';
import {useAuth} from "../authentication/AuthContext";

interface ProtectedRouteProps {
    children?: React.ReactNode;
    requiredRoles: string[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children, requiredRoles }) => {
    const { isAuthenticated, userRole } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (!isAuthenticated || !requiredRoles.includes(userRole!)) {
            navigate('/login');
        }
    }, [isAuthenticated, userRole, requiredRoles, navigate]);

    if (!isAuthenticated || !requiredRoles.includes(userRole!)) {
        return null;
    }

    return <>{children}</>;
};

export default ProtectedRoute;