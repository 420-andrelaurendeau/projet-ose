import React, {createContext, ReactNode, useContext, useEffect, useRef, useState} from 'react';
import {useNavigate} from "react-router-dom";

interface AuthContextProps {
    isAuthenticated: boolean;
    userRole: string | null;
    userEmail: string | null;
    userId: number;
    loginUser: (token: string) => void;
    logoutUser: () => void;

}

const AuthContext = createContext<AuthContextProps>({
    isAuthenticated: false,
    userRole: null,
    userEmail: "",
    loginUser: () => {
        console.log('no auth context provider');
    },
    logoutUser: () => {
    },
    userId: 0
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
    const [userId, setUserID] = useState<number>(0);
    const [userEmail, setUserEmail] = useState<string | null>("");
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);

    const fechtToken = useRef(false);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = JSON.parse(atob(token.split('.')[1]));
            setUserRole(decodedToken.role[0].authority);
            setUserID(decodedToken.id);
            setIsAuthenticated(true);
            setUserEmail(decodedToken.sub);

            navigate(window.location.pathname);

        }else {
            setIsAuthenticated(false);
            setUserRole(null);
            setUserID(0);
            navigate(`/login`);
        }
        setLoading(false);
    }, [localStorage.getItem('token')]);



    const loginUser = (token: string) => {
        localStorage.setItem('token', token);
         const decodedToken = JSON.parse(atob(token.split('.')[1]));
         console.log(decodedToken.id);
         setUserID(decodedToken.id);
         setUserEmail(decodedToken.sub);
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
        return <p></p>;
    }

    return (
        <AuthContext.Provider value={{isAuthenticated, userRole, userEmail, userId, loginUser, logoutUser}}>
            {children}
        </AuthContext.Provider>
    );
};
