import api from "./ConfigAPI";


export const saveEmployerOpinion = async (id: number, opinion: string) => {
    try {
        console.log(id, opinion)
        const response = await api.post(`stage/saveEmployerOpinion`, {stageId: id , opinion:opinion });
        console.log(response.data)
        return response.data;
    } catch (error) {
        console.error('Erreur lors de sauvegarde le l\'opinion:', error);
        throw error;
    }
}