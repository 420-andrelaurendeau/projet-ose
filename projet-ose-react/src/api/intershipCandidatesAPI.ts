import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/api/';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});


export const getInterOfferCandidates = async (id:any) => {
    try {
        console.log(id);
        const response = await apiClient.get('intershipCandidates/getInternshipCandidatesByIds/' + id);
        return response.data;


    } catch (error) {
        console.error('Erreur lors de la récupération des candidats', error);
        throw error;
    }

}