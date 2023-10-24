import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";
import {OfferReviewRequest} from "../model/OfferReviewRequest";
import {webcrypto} from "crypto";
import {AppliedOffers} from "../model/AppliedOffers";
import api from "./ConfigAPI";

const API_BASE_URL = 'http://localhost:8080/api/internshipManager/';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:3000',
    },
});


interface GetInternshipOffersParams {
    page: number;
    size: number;
    state?: string;
    sortField: string;
    sortDirection: string;
}

export const getIntershipOffers = async ({ page, size, state, sortField, sortDirection }: GetInternshipOffersParams) => {
    try {
        const params: any = { page, size, sortField, sortDirection };

        if (state) {
            params.state = state;
        }

        const response = await api.get('internshipManager/offers', {
            params: params,
            headers: {
             //'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        });
        console.log('response', response.data);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres de stage:', error);
        throw error;
    }
};

export const getTotalOfferByState = async () => {
    try {
        const response = await apiClient.get('/count', {
            headers: {
              //  'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        });
        console.log('response', response.data);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres de stage:', error);
        throw error;
    }
}

export const getOfferReviewById = async (id: number) => {
    try {
        const response = await apiClient.get(`/offer/${id}/review`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération de l\'avis de l\'offre de stage:', error);
        throw error;
    }
}

