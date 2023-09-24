import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";

const API_BASE_URL = 'http://localhost:8080/api/interOfferJob';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const saveInterOfferJob = async (interOfferJob: InterOfferJob) => {
    try {
        const response = await apiClient.post('/save', interOfferJob);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la sauvegarde de l\'InterOfferJob:', error);
        throw error;
    }
};
