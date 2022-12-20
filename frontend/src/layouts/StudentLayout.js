import { Outlet } from "react-router-dom";
import Footer from "../components/Footer";
import TopNav from "../components/TopNav";


const GlobalWrapper = ({children}) => {
    return ( <main className="min-h-screen container-padding" >
       <TopNav></TopNav>
        <Outlet></Outlet>

      <Footer></Footer>
    </main> );
}
 
export default GlobalWrapper;