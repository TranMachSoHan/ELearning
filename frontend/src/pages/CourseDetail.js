import Button from "../components/Button";
import CourseThumb from '../assets/course-demo-thumb.jpg'
import Avatar from '../assets/instructor-ava.jpg'
import SectionTitle from "../components/SectionTitle";
import { Link, useNavigate, useParams } from "react-router-dom";
import Comment from "../components/Comment";
import { Accordion } from "flowbite-react";
import CourseContent from "../components/CourseContent";
import { useEffect, useState } from "react";
import { enrollCourse, getCourseById, saveCourse } from "../api/useCourseAPI";
import {useAuth} from '../context/AuthContext'
const commentData = [
    {
        name: 'Nguyen Van Minh',
        commentDate: 'Sep 6, 2020',
        content: 'Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.',
        imgSrc: Avatar,
    },
    {
        name: 'Nguyen Ngoc',
        commentDate: 'Sep 6, 2020',
        content: 'Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.',
        imgSrc: Avatar,
    },
    {
        name: 'Cameron Williamson',
        commentDate: 'Sep 6, 2020',
        content: 'Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.',
        imgSrc: Avatar,
    },
]

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
const CourseDetail = () => {

    const {user } = useAuth();
    const navigate = useNavigate();

    let { courseID } = useParams();
    let [detail, setDetail] = useState(null)
    useEffect(() => {
          const fetchData = async () => {
           let data = await getCourseById(courseID)
           console.log(data)

           setDetail(data) 
        }

        fetchData();
    },[courseID])

    const enroll = async () => {
        if (user){
            let enrollMsg = await enrollCourse(courseID, user.id)
            navigate(`/learning/${detail.courseName}/${enrollMsg.courseProgressID}`)
        }else{
            navigate('/login')
        }
    }
    
    const save = async () => {
        if (user){
            let enrollMsg = await saveCourse(courseID, user.id)
            navigate(`/studentDetail/${user.id}`)
        }else{
            navigate('/login')
        }
    }
    


    if (detail){
        return ( <section className="pt-8">
        <div className="relative grid">
            <div className='relative block col-span-1 col-start-1 row-span-1 row-start-1 after:absolute after:inset-0 after:bg-primary-50/80 after:z-0'>
                <img src={CourseThumb} className='h-[600px] w-full object-cover ' alt="" />
        
            </div>
            <div className="col-span-1 z-[1]  self-center ml-[72px]  relative col-start-1 row-start-1 row-span-1">
                <h1 className="font-bold text-headline-48 text-primary-500">{detail.courseName}</h1>
                <p className="py-4 text-lead-24">Created by {detail.professor.name} - {detail.numberOfStudent} students enrolled</p>
                <div className="flex items-center gap-5">
                    <Button size="large" onClick={enroll} text={'Start Now!'}></Button>
                    <Button isPrimary={false} size='large' onClick={save} text={'Save Later'}></Button>
                </div>
            </div>
        </div>

        <div className="pt-12 pb-10 space-y-3">
            <SectionTitle title={'Course Description'}></SectionTitle>
            <p className="max-w-[80%]">{detail.courseDescription}</p>
        </div>
        <div className="space-y-3">
            <SectionTitle title={'Modules'}></SectionTitle>
            
            <Accordion>
                    {detail?.modules?.map((m) =>  <Accordion.Panel  key={m.moduleID}>
                        <Accordion.Title className={(m.canViewed ? "": "pointer-events-none opacity-50" )+ " text-button-21"}>
                        {m.title}
                        </Accordion.Title>
                        <Accordion.Content>
                        <p className="mb-2 text-gray-500 ">
                            <CourseContent contentTitle={'String'} duration='6:05'/>
                            <CourseContent contentTitle={'Variables'} duration='6:05'/>
                        </p>
                        
                        </Accordion.Content>
                    </Accordion.Panel>)}
            </Accordion>
        </div>
        <div className="pt-12 pb-10 space-y-3">
            <SectionTitle title={'Instructor'}></SectionTitle>
            <div className="flex items-center gap-8 max-w-[80%]">
                <img src={Avatar} alt="" className="block aspect-square rounded-full object-cover w-[168px]" />
                <div>
                    <h3 className="font-bold text-headline-26">{detail.professor.name}</h3>
                    <p className="pt-1 pb-3 font-light text-small-16">{detail.professor.numberOfCourses} courses</p>

                    <p>Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.</p>

                    <Link href="/" className="underline text-primary-500">View instructor profile</Link>
                </div>
            </div>
        </div>
        
        <div className="pt-12 pb-10 space-y-3">
            <SectionTitle title={'Comments'}></SectionTitle>
            <div className="grid grid-cols-2 gap-9">
                {detail?.comments?.map(cmt => <Comment key={cmt.id} name={cmt.id} commentDate={cmt.time} content={cmt.details} imgSrc={Avatar} ></Comment>) }
            </div>
        </div>

        <input type="text" placeholder="Leave a comment" className="block w-1/2 px-4 py-2 border border-grey-300" />
    </section> );
    }

    
}
 
export default CourseDetail;