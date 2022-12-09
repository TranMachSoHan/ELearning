import TopNav from "../components/TopNav";


const GlobalWrapper = ({children}) => {
    return ( <main className="container-padding" >
       <TopNav></TopNav>
        {children}
    </main> );
}
 
export default GlobalWrapper;