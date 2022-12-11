import { useState } from "react";
import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";
import TabItem from "../components/TabItem";

const data = [
    {   
        skill: 'Python',
        isActive: true,
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
    },
    {   
        skill: 'Excel',
        isActive: false,
        listCourses: [
            {
                courseName: 'Excel for Absolute Beginners',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Excel for Intermediate Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Excel for Senior Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
        ]
    },
    {   
        skill: 'C++',
         isActive: false,
        listCourses: [
            {
                courseName: 'C++ for Absolute Beginners',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'C++ for Intermediate Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'C++ for Senior Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
        ]
    },
    {   
        skill: 'Android Development',
         isActive: false,
        listCourses: [
            {
                courseName: 'Android Development for Absolute Beginners',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Android Development for Intermediate Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Android Development for Senior Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
        ]
    },
    {   
        skill: 'Google Analytics',
         isActive: false,
        listCourses: [
            {
                courseName: 'Google Analytics for Absolute Beginners',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Google Analytics for Intermediate Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
            {
                courseName: 'Google Analytics for Senior Students',
                courseDes: 'Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.',
                instructor: 'Hoang Minh Quan'
            },
        ]
    },
    
]

const CourseCategoryTabs = () => {
    const [courseData, setCourseData] =useState(data);
    const tabClick = (tabName) => {
        console.log(tabName);
       
        setCourseData((preCourseData) => {
            return preCourseData.map((course) => {
                if (course.skill === tabName){
                    
                    return {...course,isActive: true};
                }else{
                    return {...course,isActive: false};
                }
            })
        })

    }
    
    return ( <section className="">
        <SectionTitle title={"A broad selection of courses"}></SectionTitle>

        <div className="px-12 py-10 mt-3 border border-grey-900">
            <div className="flex items-center gap-8 mb-14">

                {courseData.map(({skill, isActive}) => <TabItem content={skill} onClick={tabClick} isActive={isActive} ></TabItem>)}
            </div>
            
            <div className="space-y-7">
               {courseData.map(({isActive, listCourses}) => {
                    if (isActive){
                        return listCourses.map(({courseName, courseDes, instructor}) => <TabContent courseName={courseName} courseDes={courseDes} instructor={instructor}/>)
                    }
               })}
               <div className="flex justify-center">

                    <Button isPrimary={true} size='large' text={`See More Courses`}></Button>
               </div>
            </div>
        </div>
    </section> );
}
 
export default CourseCategoryTabs;