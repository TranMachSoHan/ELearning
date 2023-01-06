import axios from 'axios';
import { ACCESS_TOKEN } from '../constants';
import Base from './base'


export const getStudentProfileById = async (id) => {
    try {
        const res = await axios.get(`http://localhost:8081/profile/student/${id}`)
        return res.data
    } catch (error) {
        return error
    } 
}


export const signOut = () => {
    
}