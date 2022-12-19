import { Disclosure } from '@headlessui/react'
import React from 'react'
import { GiHamburgerMenu } from "react-icons/gi"
import {
    MdOutlineAnalytics,
    MdOutlineSchool,
    MdOutlineLogout
} from "react-icons/md";
import { BsFlag } from "react-icons/bs";

function SideBar() {
    return (
        <div>
            <Disclosure as="nav">
                <Disclosure.Button className="absolute top-4 right-4 inline-flex items-center peer justify-center rounded-md p-2text text-gray-900 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:rind-white group hover:bg-gray-900">
                    <GiHamburgerMenu className="block md:hidden h-6 w-6" aria-hidden="true" />
                </Disclosure.Button>
                <div className='p-6 w-1/6 h-screen bg-white z-20 fixed top-0 -left-96 ;g:w-60 lg:left-0 peer-focus:left-0 peer:transition ease-out delay-150 duration-200'>
                    <div className="flex flex-col justify-start items-center">
                        <h2 className="font-black text-black cursor-pointer text-headline-21 lg:text-headline-31">Admin</h2>
                        <div className='my-4 border-b border-grey-100 pb-4'>
                            <div className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                                <MdOutlineAnalytics className='text-2xl text-grey-600 group-hover:text-white' />
                                <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Stats</h3>
                            </div>
                            <div className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                                <MdOutlineSchool className='text-2xl text-grey-600 group-hover:text-white' />
                                <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Courses</h3>
                            </div>
                            <div className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                                <BsFlag className='text-2xl text-grey-600 group-hover:text-white' />
                                <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Career Path</h3>
                            </div>
                        </div>
                        <div className='my-4'>
                            <div className='flex mb-2 justify-start items-center gap-4 pl-5 hover:bg-primary-500 p-2 group cursor-pointer hover:shadow-lg m-auto'>
                                <MdOutlineLogout className='text-2xl text-grey-600 group-hover:text-white' />
                                <h3 className='text-base text-grey-800 group-hover:text-white font-sans'>Log out</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </Disclosure>

        </div>
    )
}

export default SideBar