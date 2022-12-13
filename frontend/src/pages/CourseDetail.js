import Button from "../components/Button";
import CourseThumb from '../assets/course-demo-thumb.jpg'
import Avatar from '../assets/instructor-ava.jpg'
import SectionTitle from "../components/SectionTitle";
import { Link } from "react-router-dom";
import Comment from "../components/Comment";
import { Accordion } from "flowbite-react";

const commentData = [
    {
        name: 'Nguyen Van Minh',
        commentDate: 'Sep 6, 2020',
        content: 'Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.',
        imgSrc: Avatar,
    },
    {
        name: 'Nguyen Ngoc',
        commentDate: 'Sep 6, 2020',
        content: 'Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.',
        imgSrc: Avatar,
    },
    {
        name: 'Cameron Williamson',
        commentDate: 'Sep 6, 2020',
        content: 'Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.',
        imgSrc: Avatar,
    },
]

const modules = [
    'Up and Running with Python',
    'The Basics (Data Type)',
    'Condition and Loops',
    'Functions',
    'Classes & OOP',
    'File I/O',
    'Data Analysis with Pandas',
    'Quiz',
    'Wrap Up',
]
const CourseDetail = () => {
    return ( <section className="pt-8">
        <div className="grid relative">
            <div className='col-span-1  block after:absolute after:inset-0 after:bg-primary-50/80 after:z-0  relative col-start-1 row-start-1 row-span-1'>
                <img src={CourseThumb} className='h-[600px] w-full object-cover ' alt="" />
        
            </div>
            <div className="col-span-1 z-[1]  self-center ml-[72px]  relative col-start-1 row-start-1 row-span-1">
                <h1 className="text-headline-48 font-bold text-primary-500">Python for Absolute Beginners</h1>
                <p className="text-lead-24 py-4">Created by Hoang Minh Quan - 21,434 students enrolled</p>
                <div className="flex items-center gap-5">
                    <Button size="large" text={'Start Now!'}></Button>
                    <Button isPrimary={false} size='large' text={'Save Later'}></Button>
                </div>
            </div>
        </div>

        <div className="pt-12 pb-10 space-y-3">
            <SectionTitle title={'Course Description'}></SectionTitle>
            <p className="max-w-[80%]">Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.Lorem ipsum dolor sit amet consectetur. Tellus aliquet nisl neque metus sed lectus. Gravida tellus arcu amet aenean tortor eget vitae adipiscing. Vitae pharetra donec faucibus pulvinar neque etiam ultrices leo. Orci turpis mattis amet accumsan dolor.</p>
        </div>
        <div className="space-y-3">
            <SectionTitle title={'Modules'}></SectionTitle>
            
            <Accordion>
                    {modules.map((m) =>  <Accordion.Panel key={m}>
                        <Accordion.Title>
                        {m}
                        </Accordion.Title>
                        <Accordion.Content>
                        <p className="mb-2 text-gray-500 ">
                            course content goes here
                        </p>
                        
                        </Accordion.Content>
                    </Accordion.Panel>)}
            </Accordion>
        </div>
        <div className="pt-12 pb-10 space-y-3">
            <SectionTitle title={'Instructor'}></SectionTitle>
            <div className="flex items-center gap-8 max-w-[80%]">
                <img src={Avatar} alt="" className="block aspect-square rounded-full object-cover w-[168px]" />
                <div>
                    <h3 className="text-headline-26 font-bold">Hoang Minh Quan</h3>
                    <p className="font-light text-small-16 pt-1 pb-3">24 courses</p>

                    <p>Lorem ipsum dolor sit amet consectetur. Ornare augue tristique posuere laoreet eget pulvinar egestas gravida maecenas. Vel neque quis ac magna.</p>

                    <Link href="/" className="text-primary-500 underline">View instructor profile</Link>
                </div>
            </div>
        </div>
        
        <div className="pt-12 pb-10 space-y-3">
            <SectionTitle title={'Comments'}></SectionTitle>
            <div className="grid grid-cols-2 gap-9">
                {commentData.map(cmt => <Comment key={cmt.name} name={cmt.name} commentDate={cmt.commentDate} content={cmt.content} imgSrc={cmt.imgSrc} ></Comment>) }
            </div>
        </div>

        <input type="text" placeholder="Leave a comment" className="block border py-2 px-4 w-1/2 border-grey-300" />
    </section> );
}
 
export default CourseDetail;