import { Link } from "react-router-dom";

const SavedCourseCard = ({courseTitle, type='saved', courseLink='/courseDetail', percentCompleted='0', claimed=false}) => {
    return ( <div className="flex items-center justify-between py-6 bg-grey-100 px-7">
        <h5 className=""> <span className="font-bold text-headline-26">{courseTitle}</span> {type === 'inprogress' && `- ${percentCompleted}% completed`}</h5>

        {
            type ==='completed' && claimed ? 
            <p className="text-success-500">Claimed</p> : 
            
            type ==='completed' && !claimed ? 
            <p className="underline text-primary-500">Claim Certificate</p> :
            
            <div className="flex items-center gap-3">
                <Link to={courseLink} className='underline text-primary-500 text-button-21'>{type === 'saved' ? "View Course" : type==='inprogress' ? "Continue" : null}</Link>

                {
                    type=== "saved" ? 
                    
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M6.6129 5.2097C6.22061 4.90468 5.65338 4.93241 5.29289 5.29289C4.90237 5.68342 4.90237 6.31658 5.29289 6.70711L10.5858 12L5.29289 17.2929L5.2097 17.3871C4.90468 17.7794 4.93241 18.3466 5.29289 18.7071C5.68342 19.0976 6.31658 19.0976 6.70711 18.7071L12 13.4142L17.2929 18.7071L17.3871 18.7903C17.7794 19.0953 18.3466 19.0676 18.7071 18.7071C19.0976 18.3166 19.0976 17.6834 18.7071 17.2929L13.4142 12L18.7071 6.70711L18.7903 6.6129C19.0953 6.22061 19.0676 5.65338 18.7071 5.29289C18.3166 4.90237 17.6834 4.90237 17.2929 5.29289L12 10.5858L6.70711 5.29289L6.6129 5.2097Z" fill="#DF1642"/>
                    </svg> : 
                    type === 'inprogress' ? 
                    <p className="text-error-500">Drop</p> : null
                }
                

            </div>


        }

        

    </div> );
}
 
export default SavedCourseCard;