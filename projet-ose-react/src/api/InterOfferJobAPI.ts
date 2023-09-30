import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";
import {webcrypto} from "crypto";

const API_BASE_URL = 'http://localhost:8080/api/interOfferJob';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const saveInterOfferJob = async (interOfferJob: InterOfferJob) => {
    const interOfferJobDto = {
        id: interOfferJob.id,
        title: interOfferJob.title,
        location: interOfferJob.location,
        description: interOfferJob.description,
        salaryByHour: interOfferJob.salaryByHour,
        startDate: interOfferJob.startDate,
        endDate: interOfferJob.endDate,
        programmeId: interOfferJob.programmeId!,
        file: interOfferJob.file,
        employeurId: 4 //TODO à remplacer par le bon type
    }

    try {

        const response = await apiClient.post('/save', interOfferJobDto);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la sauvegarde de l\'InterOfferJob:', error);
        throw error;
    }
};

export const getInterOfferJob = async (id: number) => {
    try {
        const response = await apiClient.get('/OffersEmp/' + id);
        console.log(response.data);
        return response.data;

    } catch (error) {
        console.error('Erreur lors de la récupération des offres', error);
        throw error;
    }


}
