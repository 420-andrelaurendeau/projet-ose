import api from './ConfigAPI'
import {Message} from "../model/Message";

export const fetchUserNotifications = async (userId: number ): Promise<Message[]> => {
    try {
        const res = await api.get(`notifications/`+ userId)
        console.log(res.data)
        return res.data
    }
    catch (err) {
        console.log('Error while fetching interviews' + err)
        throw err
    }
}