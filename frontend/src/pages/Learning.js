import { Accordion } from "flowbite-react";
import { Link, useParams } from "react-router-dom";
import LearningListModules from "../components/LearningListModules";
import Demo from '../assets/video-demo.mp4'
import { useEffect, useState } from "react";
import { getStudyProgress } from "../api/useCourseAPI";
import Button from "../components/Button";
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
    const {courseProgressID, courseName} = useParams();
    const [progress, setProgress] = useState(null);
    const [article, setArticle] = useState(null);
    const [contentTitle, setContentTitle] = useState(null);
    

    useEffect(()=>{
        const getStudy = async () =>{
           let data = await getStudyProgress(courseProgressID);
           console.log(data);
           setProgress(data);
        }

        getStudy();

    }, [])

    const [videoLink, setVideoLink] = useState(null);
    const [contentType, setContentType] = useState(null);

    const [completed, setCompleted] = useState(false);

    const completeLesson = () => {
        
    }

    return ( <section>
        {/* BreadCrumb */}
        <h4 className="py-10 text-small-16 text-primary-500">{courseName}</h4>

        <div className="grid grid-cols-3">
            <div>
                <LearningListModules setCompleted={setCompleted} setArticle={setArticle} setContentTitle={setContentTitle} setVideoLink={setVideoLink} setContentType={setContentType} modules={progress?.moduleProgresses} />
                
            </div>

            <div className="col-span-2 space-y-3">
                <h2 className="text-lead-24">{contentTitle}</h2>

                <div className="w-full aspect-video">

                    {
                        contentType === "VIDEO" ? 
                             <video className="w-full h-full" controls>
                                {videoLink && <source src={videoLink} type="video/mp4"/>}
                            
                            </video>
                        : contentType === "ARTICLE" ?
                            <p>{article}</p>

                        : null
                    }
                   
                </div>

                <div className="flex justify-end">

                    {
                        !completed ? 
                        <Button text={'Complete Lesson'} onClick={completeLesson} isPrimary={true} size="large"></Button>
                        : <p className="text-success-500">
                            You have completed this lesson!
                        </p>
                    }
                    
                </div>
            </div>

        </div>

    </section> );
}
 
export default Learning;