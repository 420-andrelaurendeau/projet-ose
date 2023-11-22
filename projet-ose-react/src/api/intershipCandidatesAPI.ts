import axios from "axios";
import api from "./ConfigAPI";
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

export const saveStudentInternshipOffer = async (interOfferJob:any, student :any, cv: any) => {
    let data = {
        etudiant: student,
        internOfferJob: interOfferJob,
        files: [cv]
    }
    try {
        const response = await api.post('intershipCandidates/saveCandidats', data);
        return response.data;
    } catch (error) {
        console.log('error while saving student internship offer' + error)
        throw error
    }

}
export const getInterOfferCandidates = async (id:any) => {
    try {
        console.log(id);
        const response = await api.get('intershipCandidates/getInternshipCandidatesByIds/' + id);
        return response.data;


    } catch (error) {
        console.error('Erreur lors de la récupération des candidats', error);
        throw error;
    }

}