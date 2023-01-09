import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";
import {useEffect, useState} from 'react'
import { paginateCoursesBySkill } from "../api/useCourseAPI";
import { useParams } from "react-router-dom";
import {Pagination} from 'flowbite-react'

const CourseBySkill = () => {
    let { skillName } = useParams();
    const [currentPage, setCurrentPage] = useState(1)
    const [totalPages, setTotalPages] = useState(10)
    const PAGE_SIZE = 4
    const [listCoursesShown, setListCoursesShown] = useState(null)
    
    useEffect(() => {

        const fetchData = async () => {
            try {
                
                let data = await paginateCoursesBySkill(currentPage, PAGE_SIZE, skillName.toUpperCase())
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

    console.log(listCoursesShown);

    return ( <main className="pt-20">
        <SectionTitle title={`${skillName.toLowerCase()} Courses`} ></SectionTitle>
        <section className="px-12 py-10 mt-3 border border-grey-900">
            <div className="space-y-7">
               {listCoursesShown?.map(({courseName, professor : {name}, courseDescription, courseID}) => <TabContent key={courseID} courseName={courseName} courseDes={courseDescription} courseID={courseID} instructor={name}/>)}
               
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