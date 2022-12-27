import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";

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
    return ( <main className="pt-20">
        <SectionTitle title={`${data.skill} Courses`} ></SectionTitle>
        <section className="px-12 py-10 mt-3 border border-grey-900">
            <div className="space-y-7">
               {data.listCourses.map(({courseName, courseDes, instructor}) => <TabContent key={courseName} courseName={courseName} courseDes={courseDes} instructor={instructor}/>)}
               
            </div>
        </section>
    </main> );
}
 
export default CourseBySkill;