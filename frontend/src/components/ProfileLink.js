import { useState } from "react";
import { Link } from "react-router-dom";

const ProfileLink = ({imgSrc, name}) => {
    const [open, setOpen] = useState(false);
    return ( <div className="relative flex items-center gap-4 " onMouseEnter={() => {setOpen(true)}} onMouseLeave={() => {setOpen(false)}}>
        <img src={imgSrc} className='block rounded-full aspect-square w-9' alt="" />

        <h4>{name}</h4>

        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" clip-rule="evenodd" d="M5.29289 8.29289C5.65338 7.93241 6.22061 7.90468 6.6129 8.2097L6.70711 8.29289L12 13.585L17.2929 8.29289C17.6534 7.93241 18.2206 7.90468 18.6129 8.2097L18.7071 8.29289C19.0676 8.65338 19.0953 9.22061 18.7903 9.6129L18.7071 9.70711L12.7071 15.7071C12.3466 16.0676 11.7794 16.0953 11.3871 15.7903L11.2929 15.7071L5.29289 9.70711C4.90237 9.31658 4.90237 8.68342 5.29289 8.29289Z" fill="#171717"/>
        </svg>

        <ul className={"absolute right-0 -bottom-0.5 transform translate-y-full z-10 p-4 space-y-2 bg-white drop-shadow-lg " + (open ? 'block' : 'hidden')}>
            <li><Link>Go to Profile</Link></li>
            <li><Link>Sign Out</Link></li>
        </ul>
        
    </div> );
}
 
export default ProfileLink;