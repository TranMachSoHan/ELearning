
import { useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import CourseCategoryTabs from "../layouts/CourseCategoryTabs";
import CoursePath from "../layouts/CoursePath";
import Hero from "../layouts/Hero";


const Home = () => {
    
    const {user} = useAuth();
    return ( <main>
        <Hero></Hero>

        <CourseCategoryTabs></CourseCategoryTabs>

        {user && <CoursePath></CoursePath>}
    </main> );
}
 
export default Home;