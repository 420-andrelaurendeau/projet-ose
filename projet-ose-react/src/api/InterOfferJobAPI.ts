import axios from 'axios';
import {InterOfferJob} from "../model/IntershipOffer";
import {OfferReviewRequest} from "../model/OfferReviewRequest";
import {webcrypto} from "crypto";
import {AppliedOffers} from "../model/AppliedOffers";

const API_BASE_URL = 'http://localhost:8080/api/';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const saveInterOfferJob = async (interOfferJob: InterOfferJob, id:number) => {
    const interOfferJobDto = {
        title: interOfferJob.title,
        location: interOfferJob.location,
        description: interOfferJob.description,
        salaryByHour: interOfferJob.salaryByHour,
        startDate: interOfferJob.startDate,
        endDate: interOfferJob.endDate,
        programmeId: interOfferJob.programmeId!,
        file: interOfferJob.file,
        employeurId: id
    }

    try {

        const response = await apiClient.post('interOfferJob/save', interOfferJobDto);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la sauvegarde de l\'InterOfferJob:', error);
        throw error;
    }
};

export const getAllPendingInterOfferJob = async (): Promise<InterOfferJob[]> => {
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




export const getInterOfferJob = async (email: string) => {
    try {
        const response = await apiClient.get('interOfferJob/OffersEmp/' + email);
        return response.data;

    } catch (error) {
        console.error('Erreur lors de la récupération des offres', error);
        throw error;
    }

}

export const getStudentAppliedOffers = async (studentId: number): Promise<AppliedOffers[]> => {
    try {
        const response = await apiClient.get('/etudiant/' + studentId + '/offersApplied');
        return response.data.map((item: any) => ({
            appliedOffer: item.appliedOffer,
            appliedFiles: item.appliedFiles
        }));
    } catch (error) {
        console.error('Erreur lors de la récupération des offres auxquelles l\'étudiant a postulé:', error);
        throw error;
    }
}

export function UpdateOffers(email:string,setOffers:any){
    const loadOffers = async () => {
        try {
            const data = await getInterOfferJob(email);
            console.log(data);
            setOffers(data);
        } catch (error) {
            console.error('Erreur lors du chargement des programmes:', error);
        }
    };
    loadOffers().then(r => console.log(r))
}
