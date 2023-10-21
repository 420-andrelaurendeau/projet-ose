import React, {createContext, ReactNode, useContext, useState} from 'react';

interface AuthContextProps {
    isAuthenticated: boolean;
    userRole: string | null;
    userId: Number | null;
    login: (role: string) => void;
    logout: () => void;
}

const AuthContext = createContext<AuthContextProps>({
    isAuthenticated: false,
    userRole: null,
    userId: null,
    login: () => {},
    logout: () => {},
});

export const useAuth = () => {
    return useContext(AuthContext);
};

export const AuthProvider: React.FC<ReactNode> = (children ) => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
    const [userRole, setUserRole] = useState<string | null>(null);
    const [userId, setUserId] = useState<Number | null>(null);

    const login = (role: string) => {
        setIsAuthenticated(true);
        setUserRole(role);
    };

    const logout = () => {
        setIsAuthenticated(false);
        setUserRole(null);
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, userRole, userId, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
