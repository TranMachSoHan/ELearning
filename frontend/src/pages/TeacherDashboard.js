import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import TeacherCourseCard from "../components/TeacherCourseCard";
import TeacherNav from "../components/TeacherNav";
import {Dropdown, Modal} from 'flowbite-react'
import CourseThumb from '../assets/course-demo-thumb.jpg'
import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { createCourse } from "../api/useCourseAPI";
import { useAuth } from "../context/AuthContext";


const TeacherDashboard = () => {
    const {user} = useAuth();
    const navigate = useNavigate();
    const [openModal, setOpenModal] = useState(false)
    const [skillSelect, setSkillSelect] = useState(null)
    const [skillDisplay, setSkillDisplay] = useState("Pick a skill")
    const [courseName, setCourseName] = useState(null)
    const [courseDescription, setCourseDescription] = useState(null)

    const [listTeacherCourses, setListTeacherCourses] = useState( [
            {
                courseName: "Python for beginners",
                
                
            },
            {
                courseName: "Python for beginners",
                
                
            },

        ])
    

    const onClose = () => {
        setOpenModal(false)
    }

    const submit = async (e) => {
        e.preventDefault();

        let payload = {
            "courseName": courseName,
            "professorID": user.id,
            "courseDescription": courseDescription,
            "skill": skillSelect
        }
        
        let res = await createCourse(payload);
        setListTeacherCourses(prev => [...prev, res]);
        setOpenModal(false);
        navigate(`/edit-course/${res.courseID}`)
    }

    if (user?.roles[0] !== "PROFESSOR"){
        navigate('/')
    }else{
         return ( <section className="">
        <SectionTitle title={'Instructor Dashboard'}></SectionTitle>

        <div className="flex items-center justify-between pt-6 pb-14">
            <div className="flex h-12">
                <div className="grid h-full aspect-square place-items-center bg-primary-500">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M10 2C5.58172 2 2 5.58172 2 10C2 14.4183 5.58172 18 10 18C11.8487 18 13.551 17.3729 14.9056 16.3199L20.2929 21.7071L20.3871 21.7903C20.7794 22.0953 21.3466 22.0676 21.7071 21.7071C22.0976 21.3166 22.0976 20.6834 21.7071 20.2929L16.3199 14.9056C17.3729 13.551 18 11.8487 18 10C18 5.58172 14.4183 2 10 2ZM10 4C13.3137 4 16 6.68629 16 10C16 13.3137 13.3137 16 10 16C6.68629 16 4 13.3137 4 10C4 6.68629 6.68629 4 10 4Z" fill="white"/>
                    </svg>

                </div>
                <input type="text" placeholder="Find a course" className="block w-[500px]"/>
            </div>

            <Button text={'Create Course'} size='large' onClick={() => {setOpenModal(true)}} isPrimary={true}></Button>

            <Modal
                show={openModal}
                size="3xl"
                popup={true}
                onClose={onClose}
            >
                <Modal.Header />
                <Modal.Body>
                <div className="px-6 pb-4 space-y-6 sm:pb-6 lg:px-8 xl:pb-8">
                    <h2 className="font-bold text-gray-900 text-headline-31 ">
                    Course's Basic Information
                    </h2>
                    
                    <div  className="space-y-5">
                         <div className='space-y-2'>
                            <label htmlFor="courseName" >Course Name</label>
                            <input type="text" onChange={(e) => {setCourseName(e.target.value) }} name="courseName" id="courseName" className='block w-full p-2 border border-black' placeholder='Python for Beginners' />
                        </div>

                        <div className='space-y-2'>
                            <label htmlFor="courseDescription" >Course Description</label>
                            <textarea  name="courseDescription"  onChange={(e) => {setCourseDescription(e.target.value) }} id="courseDescription" className='block w-full p-2 border border-black' rows="5"  />
                        </div>

                        <div className='space-y-2'>
                            <label>Skill</label>
                            <Dropdown
                                label={skillDisplay}
                                inline={true}
                               
                                >
                                <Dropdown.Item  onClick={(e) => {setSkillSelect("PYTHON"); setSkillDisplay("Python") }}>
                                    Python
                                </Dropdown.Item>
                                <Dropdown.Item onClick={(e) => {setSkillSelect("REACTJS") ; setSkillDisplay("ReactJs")}}>
                                    ReactJs
                                </Dropdown.Item>
                                <Dropdown.Item onClick={(e) => {setSkillSelect("C"); setSkillDisplay("C") }}>
                                    C
                                </Dropdown.Item>
                                <Dropdown.Item onClick={(e) => {setSkillSelect("JAVA"); setSkillDisplay("Java") }}>
                                    Java
                                </Dropdown.Item >
                                <Dropdown.Item onClick={(e) => {setSkillSelect("NODEJS"); setSkillDisplay("NodeJs") }}>
                                    NodeJs
                                </Dropdown.Item>
                                </Dropdown>
                        </div>



                        <Button onClick={submit} isPrimary={true} size="large" className={'mb-2'} text={"Save Draft"}></Button>
                       
                    </div>
                    
                </div>
                </Modal.Body>
            </Modal>
        </div>

        <div className="space-y-6">
            {
                listTeacherCourses?.map(c =>  <TeacherCourseCard key={c.courseName} courseThumb={CourseThumb} courseTitle={c.courseName} stars={c.star} ></TeacherCourseCard>)
            }

           
            
        </div>
    </section> );
    }


   
}
 
export default TeacherDashboard;