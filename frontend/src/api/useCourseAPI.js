import Base from './base'

export const getAllCoursesBySkill = async (skill) => {
    try {
        const res = await Base.get(`/course/getAllBySkill?skill=${skill}`)
        return res.data
    } catch (error) {
        return error
    } 
    
}

export const getCourseById = async (id) => {
    try {
        const res = await Base.get(`/course/id/${id}`)
        return res.data
    } catch (error) {
        return error
    } 
    
}