const TeacherCourseCard = ({courseTitle, stars, courseThumb }) => {
    return ( <div className='flex justify-between pr-6 bg-white cursor-pointer drop-shadow-lg h-44'>
        
        <div className='flex gap-6 space-x-6'>
            <img src={courseThumb} className='block h-full aspect-square' alt="" />
        
            <div className='space-y-1.5'>
                <h5 className='pt-6 font-bold hover:text-primary-500 text-lead-24'>{courseTitle}</h5>
                
            </div>
        </div>
        

        <div className="pt-6">
                <p className="font-bold text-lead-24">{stars}</p>
                <p>Course Rating</p>
            </div>
    </div> );
}
 
export default TeacherCourseCard;