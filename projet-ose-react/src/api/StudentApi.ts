import api from './ConfigAPI'
import {Interview} from "../model/Interview";
import {Stage} from "../model/Stage";
export const saveCvStudent = async (id: number, cv: any) => {
    try {
        const response = await api.post(`student/addCv/${id}`, cv)
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
            stage: 0
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

export const saveStageStudent = async (stage : any)=>{
    try{
        const response = await api.post('stage/save',stage)
        return response.data
    }catch (error){
        console.log('error while saving stage')
        throw error
    }
}

export const fetchStagePending = async (studentId: number): Promise<Stage[]> => {
    try{
        const res = await api.get(`stage/pending/`+studentId)
        console.log("Im here")
        console.log(res)
        return res.data.map((item: any) => ({
            id: item.id,
            student_id: item.student_id,
            offer: item.offer,
            stateStudent: item.stateStudent,
            stateEmployeur: item.stateEmployeur
        }))
    }catch (error){
        throw error
    }
}

export const acceptStage = async (stage: any) => {
    try {
        const res = await api.post(`stage/acceptedStudent`,stage)
        return res.data
    }
    catch (err) {
        console.log('Error while accepting interview' + err)
        throw err
    }
}

export const declineStage = async (stage: any) => {
    try {
        const res = await api.post(`stage/declinedStudent`, stage)
        return res.data
    }
    catch (err) {
        console.log('Error while declining interview' + err)
        throw err
    }
}

export const fetchAllStudentCvs = async (studentId: number) => {
    try {
        const res = await api.get(`student/${studentId}/cvs`)
        return res.data
    } catch (err) {
        console.log('Error while fetching student cvs' + err)
        throw err
    }
}

export const setDefaultCv = async (studentId: number, cvId: number) => {
    try {
        const res = await api.post(`student/${studentId}/cv/${cvId}/setDefault`)
        return res.data
    } catch (err) {
        console.log('Error while setting default cv' + err)
        throw err
    }
}

export const fetchDefaultCvByStudentId = async (studentId: number) => {
    try {
        const res = await api.get(`student/${studentId}/defaultCv`)
        return res.data
    } catch (err) {
        console.log('Error while fetching default cv' + err)
        throw err
    }
}
