import Footer from "../components/Footer";
import TopNav from "../components/TopNav";


const GlobalWrapper = ({children}) => {
    return ( <main className="min-h-screen container-padding" >
       <TopNav></TopNav>
        {children}

      <Footer></Footer>
    </main> );
}
 
export default GlobalWrapper;