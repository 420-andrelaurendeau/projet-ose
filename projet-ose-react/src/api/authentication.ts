import { useNavigate } from 'react-router-dom';
import {useAuth} from "../authentication/AuthContext";

export const authenticateUser = async (username: string, password: string) => {
    const { loginUser } = useAuth();
    const navigate = useNavigate();

    try {
        const response = await fetch('yourApiUrl/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });

        const data = await response.json();

        if (data.token) {
            loginUser(data.token);
            navigate('/');
        } else {
            //TODO : handle error
        }
    } catch (error) {
        //TODO : handle error
    }
}