import React, {createContext, ReactNode, useContext, useEffect, useState} from 'react';

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

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(true);
    const [userRole, setUserRole] = useState<string | null>("internshipManager");
    const [userId, setUserId] = useState<Number | null>(1);

    /**

     useEffect(() => {
     const token = localStorage.getItem('token');
     if (token) {
     const decodedToken = JSON.parse(atob(token.split('.')[1]));
     setUserRole(decodedToken.role);
     setIsAuthenticated(true);
     }
     }, []);

     */
    const loginUser = (token: string) => {
        localStorage.setItem('token', token);
        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        setUserRole(decodedToken.role);
        setIsAuthenticated(true);
    };

    const logoutUser = () => {
        localStorage.removeItem('token');
        setUserRole(null);
        setIsAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{isAuthenticated, userRole, userId, loginUser, logoutUser}}>
            {children}
        </AuthContext.Provider>
    );
};
