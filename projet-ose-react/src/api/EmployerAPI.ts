import api from './ConfigAPI'

export const saveEmployer = async (employer: any) => {
    try {
        const response = await api.post(`auth/register/employeur`, employer)
        return response.data
    } catch (err) {
        console.log('Error while saving employer' + err)
        throw err
    }
}