import { NavLink } from 'react-router-dom';
import CourseDemoThumb from '../assets/course-demo-thumb.jpg'
const TabContent = ({courseName, courseDes, instructor, courseID}) => {
    return ( <div className="flex items-center gap-10 max-w-[80%]">
        <img src={CourseDemoThumb} alt="" className="block w-[268px] aspect-square" />

        <div>
            <NavLink to={`/courseDetail/${courseID}`}><h3 className="font-bold text-headline-31 hover:text-primary-500">{courseName}</h3></NavLink> 
            <p className="pt-3 pb-6">{courseDes}</p>
            <p className="font-light text-grey-900">By: {instructor}</p>
        </div>
    </div> );
}
 
export default TabContent;