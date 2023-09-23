import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";

// Base URL de votre API Spring Boot
const API_BASE_URL = 'http://localhost:8080/api/interOfferJob';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Fonction pour sauvegarder un InterOfferJob
export const saveInterOfferJob = async (interOfferJob: InterOfferJob) => {
    try {
        const response = await apiClient.post('/save', interOfferJob);
        return response.data;
    } catch (error) {
        // Gestion des erreurs (vous pouvez adapter selon vos besoins)
        console.error('Erreur lors de la sauvegarde de l\'InterOfferJob:', error);
        throw error;
    }
};
