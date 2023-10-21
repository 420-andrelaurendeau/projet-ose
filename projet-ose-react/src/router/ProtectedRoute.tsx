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
    }, [isAuthenticated, userRole, requiredRoles, navigate]); // Dépendances de l'effet

    if (!isAuthenticated || !requiredRoles.includes(userRole!)) {
        return null; // Vous pouvez retourner un composant de chargement ou null ici
    }

    return <>{children}</>;
};

export default ProtectedRoute;