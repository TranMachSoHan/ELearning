
import HeroIllus from '../assets/hero-illus.png'
const Hero = () => {
    return ( <section className="grid items-center grid-cols-2 min-h-[800px]">

        {/* Hero Content */}
        <div className='space-y-4'>
            <h1 className="font-bold text-headline-48 text-primary-500">Accelerate Your Learning At RMIT Now!</h1>
            <p className='text-lead-24'>Explore more than 2000 courses from various industry domains.</p>
        </div>

        <div className='justify-self-end '>
            <img src={HeroIllus} alt="" />
        </div>
            
       

    </section> );
}
 
export default Hero;