import { Disclosure } from '@headlessui/react'
import { useParams } from 'react-router-dom';
import { startLesson } from '../api/useCourseAPI';
import CourseContent from './CourseContent';


const LearningListModules = ({modules, setContentType, setVideoLink, setContentTitle, setArticle, setCurrentLessonID, setCurrentModuleID, setCompleted}) => {
    const {courseProgressID} = useParams();


    const openMaterial = async (type, material, title, lessonID, moduleID, lessonStatus) => {
        setContentType(type)
         setCurrentLessonID(lessonID)
         setContentTitle(title)
         setCurrentModuleID(moduleID)

        if (type === "VIDEO"){
           setVideoLink(material.fileURL);
           
        } else if (type === "ARTICLE"){
            setArticle(material.paragraph);
        }
        if (lessonStatus === 'FINISHED'){
            setCompleted(true);
            return;
        }else{
            setCompleted(false);
        }
        let res = await startLesson(courseProgressID, moduleID, lessonID)

        console.log(res);
        
         
        
    }

    

    return ( <div className='space-y-4'>
        {
            modules?.map(m => <Disclosure as={'div'} key={m.moduleID} >
                {({ open }) => (
                    <>
                        <Disclosure.Button className="font-bold text-black text-button-21">
                        {m.title}
                        </Disclosure.Button>
                        <Disclosure.Panel className={"space-y-2"}>
                            {
                                m.lessonProgressList?.map( ({lessonID, title, type, material, lessonStatus}) => <CourseContent key={lessonID} contentTitle={title} contentType={type} handleClick={() => {openMaterial(type, material, title, lessonID, m.moduleID, lessonStatus)}} isActive={false} duration='6:05'/> )
                            }
                            
                        </Disclosure.Panel>

                    </>                
                )}
            </Disclosure>)
            
        }
       
    </div> );
}
 
export default LearningListModules;