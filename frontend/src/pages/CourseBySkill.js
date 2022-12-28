import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";
import {useEffect, useState} from 'react'
import { paginateCoursesBySkill } from "../api/useCourseAPI";
import { useParams } from "react-router-dom";
import {Pagination} from 'flowbite-react'
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
    const [currentPage, setCurrentPage] = useState(1)
    const [totalPages, setTotalPages] = useState(10)
    const PAGE_SIZE = 4
    const [listCoursesShown, setListCoursesShown] = useState(null)
    
    useEffect(() => {

        const fetchData = async () => {
            try {
                
                let data = await paginateCoursesBySkill(currentPage, PAGE_SIZE, skillName.toLocaleUpperCase())
                setTotalPages(data.totalPages)
                setListCoursesShown(data.courses) 
            } catch (error) {
                console.log(error)
            }
           
        }

        fetchData();
        


    }, [currentPage, skillName])

   
    const onPageChange = async (e) => {
        setCurrentPage(e)

    }


    return ( <main className="pt-20">
        <SectionTitle title={`${skillName} Courses`} ></SectionTitle>
        <section className="px-12 py-10 mt-3 border border-grey-900">
            <div className="space-y-7">
               {listCoursesShown?.map(({courseName, professor : {professorName}, courseDescription, courseID}) => <TabContent key={courseID} courseName={courseName} courseDes={courseDescription} courseID={courseID} instructor={professorName}/>)}
               
            </div>

            <div className="flex justify-end">
                <Pagination 
                className="mt-7 "
                currentPage={currentPage}
                onPageChange={onPageChange}
                showIcons={true}
                totalPages={totalPages}
                />
            </div>
            
        </section>
    </main> );
}
 
export default CourseBySkill;