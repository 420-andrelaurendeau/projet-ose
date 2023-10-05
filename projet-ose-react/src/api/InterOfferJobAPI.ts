import axios from 'axios';
import {InternshipOffer} from "../model/IntershipOffer";
import {OfferReviewRequest} from "../model/OfferReviewRequest";

const API_BASE_URL = 'http://localhost:8080/api/';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const saveInterOfferJob = async (interOfferJob: InternshipOffer) => {
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
        employeurId: interOfferJob.employeurId //TODO à remplacer par le bon type
    }

    try {

        const response = await apiClient.post('interOfferJob/save', interOfferJobDto);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la sauvegarde de l\'InterOfferJob:', error);
        throw error;
    }
};

export const getAllPendingInterOfferJob = async (): Promise<InternshipOffer[]> => {
    try {
        const response = await axios.create({
            baseURL: API_BASE_URL,
            headers: {
                'Content-Type': 'application/json',
            },
        }).get('interOfferJob/allOffers');
        response.data.map((item: any) => console.log(item));
        return response.data.map((item: any) => ({
            id: item.id,
            title: item.title,
            location: item.location,
            description: item.description,
            salaryByHour: item.salaryByHour,
            startDate: new Date(item.startDate),
            endDate: new Date(item.endDate),
            internshipCandidates: item.internshipCandidates,
            file: item.file,
            state: item.state,
            programmeId: item.programmeId,
            programmeNom: item.programmeNom,
            employeurId: item.employeurId,
            employeurNom: item.employeurNom,
            employeurPrenom: item.employeurPrenom,
            employeurEntreprise: item.employeurEntreprise
        }));

    } catch (error) {
        console.error('Erreur lors de la sauvegarde de la revue de l\'offre:', error);
        throw error;
    }
}

export const saveOfferReviewRequest = async (offerReviewRequest: OfferReviewRequest) => {
    try {
        const response = await apiClient.post('offerReviewRequest/save', offerReviewRequest);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'envoi de la revue de l\'offre:', error);
        throw error;
    }
};



