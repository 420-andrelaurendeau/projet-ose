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

        const response = await apiClient.post('/save', interOfferJobDto);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la sauvegarde de l\'InterOfferJob:', error);
        throw error;
    }
};

export const getInterOfferJob = async (email: string) => {
    try {
        const response = await apiClient.get('/OffersEmp/' + email);
        console.log(response.data);
        return response.data;

    } catch (error) {
        console.error('Erreur lors de la récupération des offres', error);
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
