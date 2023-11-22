import axios from 'axios';
import api from "./ConfigAPI";

const API_BASE_URL = 'http://localhost:8080/api/programme';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getProgrammes = async () => {
    try {
        const response = await api.get('/programmes');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des progrommes:', error);
        throw error;
    }
};