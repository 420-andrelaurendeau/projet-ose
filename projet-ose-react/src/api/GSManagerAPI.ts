import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";
import {OfferReviewRequest} from "../model/OfferReviewRequest";
import {webcrypto} from "crypto";
import {AppliedOffers} from "../model/AppliedOffers";

const API_BASE_URL = 'http://localhost:8080/api/intershipManager/';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getIntershipOffers = async () => {

    try {
        const response = await apiClient.get('offers');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offers de stage:', error);
        throw error;
    }
};
