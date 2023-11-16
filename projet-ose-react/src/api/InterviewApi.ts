import api from './ConfigAPI'
import {Interview} from "../model/Interview";
import {Stage} from "../model/Stage";

export const fetchInterviewsEmployer = async (userId: number,{page, size, sortField, sortDirection}:any ): Promise<Interview[]> => {
    const params: any = {page, size, sortField, sortDirection};

    try {
        const res = await api.get(`interview/getByEmployerId/`+ userId, {
            params: params
        })
        console.log(res.data)
        return res.data
    }
    catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}