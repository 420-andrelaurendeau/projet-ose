import api from './ConfigAPI'
import {Interview} from "../model/Interview";
import {Stage} from "../model/Stage";

export const fetchInterviewsEmployer = async (userId: number, {page, size, sortField, sortDirection, season}: any): Promise<Interview[]> => {
    const params: any = {page, size, sortField, sortDirection, season};

    try {
        const res = await api.get(`interview/getByEmployerId/` + userId, {
            params: params
        })
        console.log(res.data)
        return res.data
    } catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}

export const updateInterview = async ({id, studentId, internOfferId, date, description}: any) => {
    try {
        const response = await api.put(`interview/update`, {id, studentId, internOfferId, date, description})
        return response.data
    } catch (err) {
        console.log('Error while updating interview' + err)
        throw err
    }
}

export const studentHasInterviewWithInternOffer = async (requestBody: any) => {
    try {
        const response = await api.post("interview/studentHasInterviewWithInternOffer", requestBody)
        return response.data
    }
    catch (err){
        console.log('Error while studentHasInterviewWithInternOffer' + err)
        throw err
    }

}