import { Disclosure } from '@headlessui/react'
import CourseContent from './CourseContent';


const LearningListModules = ({modules}) => {
    return ( <div className='space-y-4'>
        {
            modules.map(m => <Disclosure as={'div'} key={m} >
                {({ open }) => (
                    <>
                        <Disclosure.Button className="font-bold text-black text-button-21">
                        {m}
                        </Disclosure.Button>
                        <Disclosure.Panel className={"space-y-2"}>
                             <CourseContent contentTitle={'String'} isActive={open} duration='6:05'/>
                             
                            <CourseContent contentTitle={'Variables'} duration='6:05'/>
                        </Disclosure.Panel>

                    </>                
                )}
            </Disclosure>)
            
        }
       
    </div> );
}
 
export default LearningListModules;