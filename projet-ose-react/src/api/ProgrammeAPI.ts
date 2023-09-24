import axios from 'axios';

// Base URL de votre API Spring Boot
const API_BASE_URL = 'http://localhost:8080/api/programme';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Fonction pour sauvegarder un InterOfferJob
export const getProgrammes = async () => {
    try {
        const response = await apiClient.get('/programmes');
        return response.data;
    } catch (error) {
        // Gestion des erreurs (vous pouvez adapter selon vos besoins)
        console.error('Erreur lors de la récupération des progrommes:', error);
        throw error;
    }
};