import { useNavigate } from 'react-router-dom';
import {useAuth} from "../authentication/AuthContext";

export const authenticateUser = async (email: string, password: string, loginUser: any, navigate: any) => {

    try {
        const response = await fetch('http://localhost:8080/api/auth/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',

            },
            body: JSON.stringify({ email, password }),
        });

        const data = await response.json();
        console.log(data)

        if (data.token) {
            console.log(data.token + " TOKEENNNNNn«««««««««««««««««««««")
            loginUser(data.token);
            const decodedToken = JSON.parse(atob(data.token.split('.')[1]));
            return decodedToken.role[0].authority;

        } else {
            //TODO : handle error
        }
    } catch (error) {
        console.log(error)
        //TODO : handle error
    }
}