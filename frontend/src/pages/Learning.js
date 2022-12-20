import { Accordion } from "flowbite-react";
import { Link } from "react-router-dom";
import CourseContent from "../components/CourseContent";
import LearningListModules from "../components/LearningListModules";
import Demo from '../assets/video-demo.mp4'
const modules = [
    'Up and Running with Python',
    'The Basics (Data Type)',
    'Condition and Loops',
    'Functions',
    'Classes & OOP',
    'File I/O',
    'Data Analysis with Pandas',
    'Quiz',
    'Wrap Up',
]
const Learning = () => {
    return ( <section>
        {/* BreadCrumb */}
        <h4 className="py-10 text-small-16"><Link to={'/courseDetail'} className=' text-primary-500'>Python for Absolute Beginners / </Link> 

            Up and Running with Python 
        </h4>

        <div className="grid grid-cols-3">
            <div>
                <LearningListModules modules={modules} />
                
            </div>

            <div className="col-span-2 space-y-3">
                <h2 className="text-lead-24">What is Python?</h2>

                <div className="w-full aspect-video">
                    <video className="w-full h-full" controls>
                        <source src={Demo} type="video/mp4"/>
                    
                    </video>
                </div>
            </div>

        </div>

    </section> );
}
 
export default Learning;