import { Outlet } from "react-router-dom";
import Footer from "../components/Footer";
import TeacherNav from "../components/TeacherNav";
import TopNav from "../components/TopNav";


const GlobalWrapper = ({children}) => {
    return ( <main className="min-h-screen bg-grey-50 container-padding pb-[72px]" >
       <TeacherNav></TeacherNav>
        <Outlet></Outlet>

      
    </main> );
}
 
export default GlobalWrapper;