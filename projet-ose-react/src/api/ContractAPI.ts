import api from "./ConfigAPI";

export const employeurGetContractById = async (id: number) => {
    try {
        const response = await api.get(`contract/${id}`);
        console.log(response.data)
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des contrats:', error);
        throw error;
    }
}



