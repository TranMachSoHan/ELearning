import { Disclosure } from '@headlessui/react'
import CourseContent from './CourseContent';


const LearningListModules = ({modules, setContentType, setVideoLink, setContentTitle, setArticle, setCompleted}) => {

    const openMaterial = (type, material, title) => {
         setContentType(type)
         setContentTitle(title)
        if (type === "VIDEO"){
           setVideoLink(material.fileURL);
           
        } else if (type === "ARTICLE"){
            setArticle(material.paragraph)
        }
        
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
                                m.lessonProgressList?.map( ({lessonID, title, type, material}) => <CourseContent key={lessonID} contentTitle={title} contentType={type} handleClick={() => {openMaterial(type, material, title)}} isActive={false} duration='6:05'/> )
                            }
                            
                        </Disclosure.Panel>

                    </>                
                )}
            </Disclosure>)
            
        }
       
    </div> );
}
 
export default LearningListModules;