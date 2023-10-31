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
            internOffer: item.internOffer,
            date: item.date,
            description: item.description,
            state: item.state,
        }))
    }
    catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}

export const fetchInterviewsCountForStudent = async (userId: number): Promise<number> => {
    try {
        const res = await api.get(`interview/getCountByStudentId/`+ userId)
        return res.data
    }
    catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}

export const acceptInterview = async (interviewId: number, studentId: number) => {
    try {
        const res = await api.post(`interview/studentAcceptsInterviewByStudentId/${studentId}/${interviewId}`)
        return res.data
    }
    catch (err) {
        console.log('Error while accepting interview' + err)
        throw err
    }
}

export const declineInterview = async (interviewId: number, studentId: number) => {
    try {
        const res = await api.post(`interview/studentDeclineInterviewByStudentId/${studentId}/${interviewId}`)
        return res.data
    }
    catch (err) {
        console.log('Error while declining interview' + err)
        throw err
    }
}