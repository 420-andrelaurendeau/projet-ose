import api from "./ConfigAPI";

export const getUser = async (email: string) => {
    try {
        console.log(localStorage.getItem('token'))
        const response = await api.get(`utilisateur/utilisateur/${email}`);
        return response.data;
    } catch (error) {
        console.error('Erreur lors de la récupération des progrommes:', error);
        throw error;
    }
};