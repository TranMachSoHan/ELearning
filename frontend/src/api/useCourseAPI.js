import Base from './base'

export const getAllCoursesBySkill = async (skill) => {
    try {
        const res = await Base.get(`/course/getAllBySkill?skill=${skill}`)
        return res.data
    } catch (error) {
        return error
    } 
    
}

export const getCoursesProgressByStdID = async (studentID) => {
    try {
        const res = await Base.get(`/courseProgress/student/getInProgress?studentId=${studentID}`)
        return res.data
    } catch (error) {
        return error
    } 
    
}


export const getStudyProgress = async (progressID) => {
    try {
        const res = await Base.get(`/courseProgress/id/${progressID}/study`)
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
        const res = await Base.get(`course/overview/id/${id}`)
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

export const enrollCourse = async (courseID, studentID) => {
    try {
        const res = await Base.post(`/course/id/${courseID}/enroll?studentId=${studentID}`, )
        return res.data
    } catch (error) {
        return error
    } 
    
}


