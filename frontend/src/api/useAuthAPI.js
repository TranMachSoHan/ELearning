import { ACCESS_TOKEN } from '../constants';
import Base from './base'



export const userAth = () => {
    return JSON.parse(localStorage.getItem(ACCESS_TOKEN));
    
}


export const signOut = () => {
    
}