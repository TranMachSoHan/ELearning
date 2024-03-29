import axios from 'axios';
import { ACCESS_TOKEN } from '../constants';
import Base from './base'


export const getStudentProfileById = async (id) => {
    try {
        const res = await axios.get(`http://ec2-3-110-89-38.ap-south-1.compute.amazonaws.com:8081/profile/student/${id}`)
        return res.data
    } catch (error) {
        return error
    } 
}



export const getTeacherProfileById = async (id) => {
    try {
        const res = await axios.get(`http://ec2-3-110-89-38.ap-south-1.compute.amazonaws.com:8081/profile/professor/${id}`)
        return res.data
    } catch (error) {
        return error
    } 
}