import axios from 'axios';
import {InternshipOffer} from "../model/IntershipOffer";
import {OfferReviewRequest} from "../model/OfferReviewRequest";
import {AppliedOffers} from "../model/AppliedOffers";
import api from "./ConfigAPI";
import {useAuth} from "../authentication/AuthContext";

const API_BASE_URL = 'http://localhost:8080/api/';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:3000',
    },
});

export async function offresEtudiant(setOffers: any, setTotalPages: any, params: {}) {
    const loadOffers = async () => {
        try {
            console.log(params)
            const data = await getInterOfferStudent(params);
            console.log(data);
            setOffers(data.content);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.error('Erreur lors du chargement des offres:', error);
        }
    };
    await loadOffers()
}

export const allStudentInternshipOffersBySeason = async (selectedOption: string): Promise<any[]> => {
    try {
        const response = await api.get('interOfferJob/student/season/'+selectedOption);
        return response.data;
    } catch (err) {
        console.log('Error while getting interOfferJob/allOffers', err);
        throw err;
    }
};

export const allStudentInternshipOffers = async (): Promise<any[]> => {
    try {
        const response = await api.get('interOfferJob/allOffers');
        return response.data
    } catch (err) {
        console.log('Error while getting interOfferJob/allOffers' + err)
        throw err
    }
}

export const allStudentOffers = async (): Promise<any[]> => {
    try {
        const response = await api.get('interOfferJob/student/allOffers');
        return response.data
    } catch (err) {
        console.log('Error while getting interOfferJob/allOffers' + err)
        throw err
    }
}

//const response = await apiClient.get('interOfferJob/OffersEtudiant'); Robin

export const saveInterOfferJob = async (interOfferJob: InternshipOffer, id: number) => {
    const interOfferJobDto = {
        title: interOfferJob.title,
        location: interOfferJob.location,
        description: interOfferJob.description,
        salaryByHour: interOfferJob.salaryByHour,
        startDate: interOfferJob.startDate,
        endDate: interOfferJob.endDate,
        programmeId: interOfferJob.programmeId!,
        file: interOfferJob.file,
        employeurId: id,
    }

    try {

        const response = await api.post('interOfferJob/save', interOfferJobDto);
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
        const response = await api.post('offerReviewRequest/save', offerReviewRequest);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'envoi de la revue de l\'offre:', error);
        throw error;
    }
};

export const updateOfferReviewApi = async (updatedFormState :any) => {
    try {
        console.log("Update")
        const response = await api.post('offerReviewRequest/update', updatedFormState);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'update de la revue de l\'offre:', error);
        throw error;
    }
};
export const getOfferReviewRequestById = async (id: number) => {
    try {

        const response = await api.get(`offerReviewRequest/get/${id}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'envoi de la revue de l\'offre:', error);
        throw error;
    }
};


export const getInterOfferJob = async (email: string, params:{}) => {
    try {
        const response = await api.get('interOfferJob/OffersEmp/' + email,{
            params: params
        });
        return response.data;

    } catch (error) {
        console.error('Erreur lors de la récupération des offres', error);
        throw error;
    }

}

export const allEmployeurInternshipOffersBySeason = async (selectedOption: string,email: string)=>{
    try {
        const response = await api.get('interOfferJob/'+email+'/season/'+selectedOption);
        return response.data;
    } catch (err) {
        console.log('Error while getting interOfferJob/allOffers', err);
        throw err;
    }
}

export async function getEmployeurSeason(email: string){
    try{
        const response = await api.get('interOfferJob/'+email+'/getSeason');
        console.log('data:'+response.data)
        return response.data;
    }catch (error){
        console.error('Erreur lors de la recherche de saisons')
        throw error;
    }
}

export async function getEmployeurOffers(email: string){
    try{
        const response = await api.get('interOfferJob/'+email+'/getOffers');
        return response.data;
    }catch (error){
        console.error('Erreur lors de la recherche de saisons')
        throw error;
    }
}
export const getInterOfferStudent = async (params:{}) => {
    try {
        const response = await api.get('interOfferJob/OffersEtudiant',{
            params: params
        });
        console.log(params)
        return response.data;

    } catch (error) {
        console.error('Erreur lors de la récupération des offres', error);
        throw error;
    }
}


export const getPageStudentAppliedOffers = async (studentId: number, page: number, size: number, sortField: string, sortDirection: string, session: string ): Promise<AppliedOffers[]> => {
    try {
        const params: any = { page, size, sortField, sortDirection, session };

        const response = await api.get('/student/' + studentId + '/offersPageApplied', {
            params: params
        });

        console.log(response);

        return response.data.content.map((item: any) => ({
            appliedOffer: item.appliedOffer,
            appliedFiles: item.appliedFiles
        }));
        /**
         return response.data.map((item: any) => ({
         appliedOffer: item.appliedOffer,
         appliedFiles: item.appliedFiles
         }));
         */
    } catch (error) {
        console.error('Erreur lors de la récupération des offres auxquelles l\'étudiant a postulé:', error);
        throw error;
    }
}

export const getStudentAppliedOffers = async (studentId: number): Promise<AppliedOffers[]> => {
    try {


        const response = await api.get('/student/' + studentId + '/offersApplied', {

        });


         return response.data.map((item: any) => ({
         appliedOffer: item.appliedOffer,
         appliedFiles: item.appliedFiles
         }));

    } catch (error) {
        console.error('Erreur lors de la récupération des offres auxquelles l\'étudiant a postulé:', error);
        throw error;
    }
}

export function UpdateOffers(email: string, setOffers: any, setTotalPages:any, params:{}) {
    const loadOffers = async () => {
        try {
            const data = await getInterOfferJob(email, params);
            console.log(data);
            setOffers(data.content);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.error('Erreur lors du chargement des offres:', error);
        }
    };
    loadOffers().then(r => console.log(r))
}


export async function getOfferById(id: number) {
    try {
        const response = await api.get('internshipManager/offer/' + id);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres auxquelles l\'étudiant a postulé:', error);
        throw error;
    }
}

export async function getOfferApprovedSeasons(){
    try{
        const response = await api.get('interOfferJob/getOfferApprovedSeasons');
        console.log(response.data)
        return response.data;
    }catch (error){
        console.error('Erreur lors de la recherche de saisons')
        throw error;
    }
}

export async function getAllSeasons(){
    try{
        const response = await api.get('interOfferJob/getAllPossibleSeasons');
        console.log(response.data)
        return response.data;
    }catch (error){
        console.error('Erreur lors de la recherche de saisons')
        throw error;
    }
}

export async function getAllOffers(){
    try{
        const response = await api.get('interOfferJob/getAllOffers');
        return response.data;
    }catch (error){
        console.error('Erreur lors de la recherche de saisons')
        throw error;
    }
}

export async function getOffersBySeason(session: string){
    try{
        const response = await api.get('interOfferJob/'+session+'/all');
        return response.data;
    }catch (error){
        console.error('Erreur lors de la recherche de saisons')
        throw error;
    }
}