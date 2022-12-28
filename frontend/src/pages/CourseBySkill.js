import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";
import {useEffect, useState} from 'react'
import { getAllCoursesBySkill } from "../api/useCourseAPI";
import { useParams } from "react-router-dom";

const data = {
    skill: "Python",
    listCourses: [
            {
                courseName: 'Python for Absolute Beginners',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Python for Intermediate Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Python for Senior Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
    ]
}
const CourseBySkill = () => {
    let { skillName } = useParams();
    

    const [listCourses, setLitCourses] = useState(null)
    
    useEffect(() => {

        const fetchData = async () => {
           let data = await getAllCoursesBySkill(skillName.toLocaleUpperCase())
           console.log(data)

           setLitCourses(data) 
        }

        fetchData();
        


    }, [skillName])


    return ( <main className="pt-20">
        <SectionTitle title={`${data.skill} Courses`} ></SectionTitle>
        <section className="px-12 py-10 mt-3 border border-grey-900">
            <div className="space-y-7">
               {listCourses?.map(({courseName, professor : {professorName}, courseDescription, courseID}) => <TabContent key={courseID} courseName={courseName} courseDes={courseDescription} courseID={courseID} instructor={professorName}/>)}
               
            </div>
        </section>
    </main> );
}
 
export default CourseBySkill;