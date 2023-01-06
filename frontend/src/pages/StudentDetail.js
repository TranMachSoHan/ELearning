import Avatar from '../assets/instructor-ava.jpg'
import CourseThumb from '../assets/course-demo-thumb.jpg'
import { Tabs } from 'flowbite-react';
import SavedCourseCard from '../components/SavedCourseCard';
import SectionTitle from '../components/SectionTitle';
import Certificate from '../components/Certificate';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getCoursesProgressByStdID } from '../api/useCourseAPI';
import { getCurrentUser } from '../Utils/APIUltils';

const StudentDetail = () => {
    const {user} = useAuth();
    const [progress, setProgress] = useState(null);
    const navigate = useNavigate();
    useEffect(() => {
        
        getCurrentUser()
        .then((res) => {console.log(res)} )
        .catch(err => console.log(err))
        const getProgress = async () =>{
            let prg= await getCoursesProgressByStdID(user.id);
            setProgress(prg);
            console.log(prg)
        }

        getProgress();


       
    }, []) 

    if (!user){
        navigate('/')

    }else{
        return ( <section className='pt-10'>
        <div className='bg-primary-50 h-[600px] grid mb-10'>
            <div className='flex items-center self-center gap-7 container-padding-left'>
                <img src={Avatar} className='block aspect-square w-[148px] rounded-full' alt="" />
                <div>
                    <h1 className='font-bold text-primary-500 text-headline-48'>Hoang Minh Quan</h1>
                    <p className='mt-3 mb-5 text-lead-24'>Major: Software Engineering - Minor: N/A</p>

                    <p className="flex items-center cursor-pointer text-primary-500 text-button-21">
                        <span>Edit my info</span>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" clip-rule="evenodd" d="M21.2071 2.79288C19.9882 1.57392 18.0118 1.57392 16.7929 2.79288L15.2932 4.2926C15.2931 4.29269 15.293 4.29278 15.2929 4.29288C15.2928 4.29297 15.2927 4.29306 15.2926 4.29315L8.29289 11.2929L8.20608 11.392C8.07316 11.5655 8 11.779 8 12V15L8.00673 15.1166C8.06449 15.6139 8.48716 16 9 16H12L12.1314 15.9913C12.3481 15.9626 12.5508 15.8634 12.7071 15.7071L19.7071 8.70709L21.2071 7.20709L21.3423 7.06317C22.4243 5.83723 22.3792 3.96494 21.2071 2.79288ZM18.9999 6.58568L19.7929 5.79288C20.1995 5.38625 20.2286 4.74503 19.88 4.30489L19.7929 4.20709C19.3863 3.80047 18.745 3.77142 18.3049 4.11996L18.2071 4.20709L17.4142 4.99997L18.9999 6.58568ZM16 6.41417L17.5855 7.99974L11.585 13.999H10V12.414L16 6.41417ZM9 5.99998C9.55228 5.99998 10 6.4477 10 6.99998C10 7.51282 9.61396 7.93549 9.11662 7.99325L9 7.99998H6C5.48716 7.99998 5.06449 8.38602 5.00673 8.88336L5 8.99998V18C5 18.5128 5.38604 18.9355 5.88338 18.9933L6 19H15C15.5128 19 15.9355 18.6139 15.9933 18.1166L16 18V15C16 14.4477 16.4477 14 17 14C17.5128 14 17.9355 14.386 17.9933 14.8834L18 15V18C18 19.5977 16.7511 20.9036 15.1763 20.9949L15 21H6C4.40232 21 3.09634 19.7511 3.00509 18.1763L3 18V8.99998C3 7.4023 4.24892 6.09632 5.82373 6.00507L6 5.99998H9Z" fill="#3D53F5"/>
                        </svg>
                    </p>
                </div>
                
            </div>
        </div>

        <Tabs.Group
        aria-label="Tabs with underline"
        style="underline"
        >
            <Tabs.Item title="Saved Courses">
                <div className='space-y-4'>
                    {/* <SavedCourseCard courseTitle={'Python for Absolute Beginners'}></SavedCourseCard>
                     */}
                </div>
               
            </Tabs.Item>

            <Tabs.Item title="In-progress Courses">
                <div className='space-y-4'>
                    {
                        progress?.map(({course: {courseName}, finishedPercentage}) => <SavedCourseCard courseTitle={courseName} type='inprogress' percentCompleted={finishedPercentage}/>)
                    }
                </div>
            </Tabs.Item>
            <Tabs.Item title="Completed Courses">
                <div className='space-y-4'>
                    {/* <SavedCourseCard courseTitle={'Python for Absolute Beginners'} type='completed' claimed={false}></SavedCourseCard> */}
                    
                </div>
            </Tabs.Item>
            
        </Tabs.Group>


        <div className='mt-10 space-y-3'>
            <SectionTitle title={'My Certificates'}></SectionTitle>

            <div>
                <Certificate imgSrc={CourseThumb} certiTitle={'Completion of Agile Fundamentals'}></Certificate>
            </div>
        </div>
    </section> );
    }

    
}
 
export default StudentDetail;