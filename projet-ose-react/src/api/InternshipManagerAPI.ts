import api from "./ConfigAPI";
import {getOfferById} from "./InterOfferJobAPI";
import {useToast} from "../hooks/state/useToast";

interface GetInternshipOffersParams {
    page: number;
    size: number;
    state?: string;
    sortField: string;
    sortDirection: string;
    session: string;
}

export const getIntershipOffers = async ({ page, size, state, sortField, sortDirection, session }: GetInternshipOffersParams) => {
    try {
        const params: any = { page, size, sortField, sortDirection, session };

        if (state) {
            params.state = state;
        }
        console.log(session + "SESSIOn")
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

export const getStudentPendingCv = async () => {
    try {
        const response = await api.get(`internshipManager/studentCv/pending`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des CV en attente:', error);
        throw error;
    }
}

export const acceptStudentCv = async (id: number) => {
    try {
        const response = await api.post(`internshipManager/studentCv/${id}/accept`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'acceptation du CV:', error);
        throw error;
    }
}

export const declineStudentCv = async (id: number) => {
    try {
        const response = await api.post(`internshipManager/studentCv/${id}/decline`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de l\'acceptation du CV:', error);
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

export const getStageCountByStateEmployeur = async (id:number) => {
    try {
        console.log(id)
        const response = await api.get(`stage/countEmployeur/${id}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres de stage:', error);
        throw error;
    }
}

export const getStageCountByStateStudent = async (id:number) => {
    try {
        console.log(id)
        const response = await api.get(`stage/countStudent/${id}`);
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
    session: string
}

export const getStages = async ({ page, size, state, sortField, sortDirection,session }: GetInternshipOffersParams) => {

    try {
        const params: any = { page, size, sortField, sortDirection, session };

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

export const getStageByEmployeurId = async ({page, size, state, sortField, sortDirection,session}: GetInternshipOffersParams, id: number) => {
    try {
        const params: any = {page, size, sortField, sortDirection,session};
        if (state) {
            params.state = state;
        }
        const response = await api.get(`stage/employeurStage/`+id, {
            params: params
        });
        console.log('response', response.data)
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres de stage:', error);
        throw error;
    }
}

export const getStageByStudentId = async ({page, size, state, sortField, sortDirection}: GetInternshipOffersParams, id: number) => {
    try {
        const params: any = {page, size, sortField, sortDirection};
        if (state) {
            params.state = state;
        }
        const response = await api.get(`stage/studentStage/${id}`, {
            params: params
        });
        console.log('response', response.data)
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des offres de stage:', error);
        throw error;
    }
}

export const getContractById = async (id: string) => {
    try {
        console.log(id);
        const response = await api.get(`contract/${id}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération de l\'entente de stage:', error);
        throw error;
    }
}


export const signDocument = async (form: any) => {
    try {
        const response = await api.post(`contract/save`, form);
        console.log('response', response.data);
        return response.data;

    } catch (error) {
        console.error('Erreur lors de la récupération de l\'entente de stage:', error);
        throw error;
    }
}
