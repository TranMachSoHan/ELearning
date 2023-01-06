import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";
import TabContent from "../components/TabContent";

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
    
    
]
const CoursePath = () => {
    return ( <section className="mt-20">
         <SectionTitle title={"Web Development Career Path"}></SectionTitle>
        
        <div className="px-12 py-10 mt-3 border border-grey-900">
            
            <div className="space-y-7">
               {data.map(({ listCourses}) => {
                    
                        return listCourses.map(({courseName, courseDes, instructor}) => <TabContent courseName={courseName} courseDes={courseDes} instructor={instructor} key={courseName}/>)
                    
               })}
               <div className="flex justify-center">

                    <Button isPrimary={true} size='large' className={'capitalize'} text={`View full path`}></Button>
               </div>
            </div>
        </div>
    </section> );
}
 
export default CoursePath;