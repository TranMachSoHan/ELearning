import React, { useState } from 'react'
import {
    MdOutlineAnalytics,
    MdOutlineSchool,
    MdOutlineLogout
} from "react-icons/md";
import { BsFlag } from "react-icons/bs";
import { NavLink } from "react-router-dom";

function SideBar() {
    const [path, setPath] = useState('')
    return (
        <div>
            <div className='flex flex-col h-full space-y-5 justify-between min-h-screen w-60 px-2 py-4 bg-white'>
                <div className="flex flex-col justify-start items-center">
                    <h2 className="font-black text-black cursor-pointer text-headline-21 lg:text-headline-31">Admin</h2>
                    <div className='w-full border-b border-grey-100'>
                        <NavLink to={'/stats'} onClick={(e) => { setPath('stats') }} className={'flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto' + (path === 'stats' ? 'bg-primary-500' : '')}>
                            <MdOutlineAnalytics className='text-2xl text-grey-600 group-hover:text-white' />
                            <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Stats</h3>
                        </NavLink>
                        <NavLink to={'/course'} className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                            <MdOutlineSchool className='text-2xl text-grey-600 group-hover:text-white' />
                            <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Courses</h3>
                        </NavLink>
                        <NavLink to={'/career'} className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                            <BsFlag className='text-2xl text-grey-600 group-hover:text-white' />
                            <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Career Path</h3>
                        </NavLink>
                    </div>
                    <div class='w-full'>
                        <div className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                            <MdOutlineLogout className='text-2xl text-grey-600 group-hover:text-white' />
                            <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Log out</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default SideBar