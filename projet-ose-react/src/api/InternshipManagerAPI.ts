import api from "./ConfigAPI";

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

