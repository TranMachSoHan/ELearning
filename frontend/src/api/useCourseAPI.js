import Base from './base'

export const getAllCoursesBySkill = async (skill) => {
    try {
        const res = await Base.get(`/course/getAllBySkill?skill=${skill}`)
        return res.data
    } catch (error) {
        return error
    } 
    
}

export const paginateCoursesBySkill = async (pageNum, pageSize, skill) => {
    try {
        const res = await Base.get(`/course/pageableBySkill?pageNum=${pageNum}&pageSize=${pageSize}&skill=${skill}`)
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
export const getCourseBySkillGroups = async () => {
    try {
        const res = await Base.get(`/course/getAllGroupingBySkill`)
        return res.data
    } catch (error) {
        return error
    } 
    
}