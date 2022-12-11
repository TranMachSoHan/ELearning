import CourseDemoThumb from '../assets/course-demo-thumb.jpg'
const TabContent = ({courseName, courseDes, instructor}) => {
    return ( <div className="flex items-center gap-10 max-w-[80%]">
        <img src={CourseDemoThumb} alt="" className="block w-[268px] aspect-square" />

        <div>
            <h3 className="font-bold text-headline-31">{courseName}</h3>
            <p className="pt-3 pb-6">{courseDes}</p>
            <p className="font-light text-grey-900">By: {instructor}</p>
        </div>
    </div> );
}
 
export default TabContent;