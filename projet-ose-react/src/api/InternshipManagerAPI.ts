import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";
import {OfferReviewRequest} from "../model/OfferReviewRequest";
import {webcrypto} from "crypto";
import {AppliedOffers} from "../model/AppliedOffers";
import api from "./ConfigAPI";
import {getOfferById} from "./InterOfferJobAPI";

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
        const response = await api.get('internshipManager/count', {
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
        const response = await api.get(`internshipManager/offer/${id}/review`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération de l\'avis de l\'offre de stage:', error);
        throw error;
    }
}


export const getStageCountByState = async () => {
    try {
        const response = await api.get('stage/count');
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres de stage:', error);
        throw error;
    }
}

interface GetInternshipOffersParams {
    page: number;
    size: number;
    state?: string;
    sortField: string;
    sortDirection: string;
}

export const getStages = async ({ page, size, state, sortField, sortDirection }: GetInternshipOffersParams) => {

    try {
        const params: any = { page, size, sortField, sortDirection };

        if (state) {
            params.state = state;
        }

        const response = await api.get('stage/stages', {
            params: params,
        });
        console.log('response', response.data);

        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des entente de stage:', error);
        throw error;
    }
};


export const getStudentById = async (id: number) => {
    try {
        const response = await api.get(`student/${id}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération de l\'étudiant:', error);
        throw error;
    }
}

export const getEmployerById = async (employerId: any) => {
    try {
        const response = await api.get(`employeur/${employerId}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération de l\'employeur:', error);
        throw error;
    }
}

