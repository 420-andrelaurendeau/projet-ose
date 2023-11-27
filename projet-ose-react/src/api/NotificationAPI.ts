import api from './ConfigAPI'
import {Interview} from "../model/Interview";
import {Stage} from "../model/Stage";

export const fetchUserNotifications = async (userId: number ): Promise<Interview[]> => {
    try {
        const res = await api.get(`api/notifications/`+ userId)
        console.log(res.data)
        return res.data
    }
    catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}