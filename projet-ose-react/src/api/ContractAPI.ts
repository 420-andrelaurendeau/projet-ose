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

export const employeurSaveContract = async (contract: any, userRole: string) => {
    try {
        const response = await api.post(`contract/${userRole}/save`, contract);
        console.log(response.data)
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la création du contrat:', error);
        throw error;
    }
}



