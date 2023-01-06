import { useNavigate } from "react-router-dom";
import ProfileLink from "./ProfileLink";
import Avatar from '../assets/instructor-ava.jpg'

const TeacherNav = () => {
     const navigate = useNavigate();
     
    return ( <nav className="flex items-center justify-between py-14">
         {/* Logo */}
        <h4 className="font-black text-black cursor-pointer text-headline-21 lg:text-headline-31" onClick={() => {navigate('/')}}>Rademy</h4>

        <ProfileLink name={'Quan'} role='teacher' imgSrc={Avatar}></ProfileLink>
    </nav> );
}
 
export default TeacherNav;