import api from './ConfigAPI'
import {Interview} from "../model/Interview";
export const saveCvStudent = async (matricule: number, cv: any) => {
    try {
        const response = await api.post(`etudiant/addCv/${matricule}`, cv)
        return response.data
    } catch (err) {
        console.log('Error while saving cv' + err)
        throw err
    }
}

export const fetchInterviews = async (userId: number): Promise<Interview[]> => {
    try {
        const res = await api.get(`interview/getByStudentId/`+ userId)
        return res.data.map((item: any) => ({
            id: item.id,
            student: item.student,
            internshipOffer: item.internshipOffer,
            date: item.date,
            description: item.description,
        }))
    }
    catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}

