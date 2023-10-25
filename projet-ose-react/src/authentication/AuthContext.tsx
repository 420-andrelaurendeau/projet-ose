import React, {createContext, ReactNode, useContext, useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";

interface AuthContextProps {
    isAuthenticated: boolean;
    userRole: string | null;
    userId: Number | null;
    loginUser: (token: string) => void;
    logoutUser: () => void;
}

const AuthContext = createContext<AuthContextProps>({
    isAuthenticated: false,
    userRole: null,
    userId: null,
    loginUser: () => {
    },
    logoutUser: () => {
    },
});

export const useAuth = () => {
    return useContext(AuthContext);
};

interface AuthProviderProps {
    children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({children}) => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
    const [userRole, setUserRole] = useState<string | null>("");
    const [userId, setUserId] = useState<Number | null>(1);

    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = JSON.parse(atob(token.split('.')[1]));

            setUserRole(decodedToken.role[0].authority);
            setIsAuthenticated(true);
        }else {
            setIsAuthenticated(false);
            setUserRole(null);
        }
        setLoading(false);
    }, []);



    const loginUser = (token: string) => {
        localStorage.setItem('token', token);
        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        console.log(decodedToken);
        console.log(decodedToken.role[0].authority)
        setUserRole(decodedToken.role[0].authority);
        setIsAuthenticated(true);
        console.log(userRole);
    };

    const logoutUser = () => {
        localStorage.removeItem('token');
        setUserRole(null);
        setIsAuthenticated(false);
    };

    if (loading) {
        return <></>;
    }

    return (
        <AuthContext.Provider value={{isAuthenticated, userRole, userId, loginUser, logoutUser}}>
            {children}
        </AuthContext.Provider>
    );
};
