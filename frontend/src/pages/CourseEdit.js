import { Accordion, Modal } from "flowbite-react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import { createModule } from "../api/useCourseAPI";
import Button from "../components/Button";
import CourseContent from "../components/CourseContent";
import SectionTitle from "../components/SectionTitle";

const CourseEdit = () => {

    const {courseID} = useParams();
    const [lessonType, setLessonType] = useState(null);
    const [moduleTitle, setModuleTitle] = useState(null);

    const [openEditCourseName, setOpenEditCourseName] = useState(false)
    const [openEditCourseDescription, setOpenEditCourseDescription] = useState(false)
    const [openLessonForm, setOpenLessonForm] = useState(false)
    const [uploadedFile, setUploadedFile] = useState([])
    const [lessonList, setLessonList] = useState([])
    const [lessonName, setLessonName] = useState("")
    const [lessonDuration, setLessonDuration] = useState("")
    const [lessonArticle, setLessonArticle] = useState("")

    const [openModal, setOpenModal] = useState(false)

    const onClose = () => {
        setOpenModal(false)
    }
    
    const handleSubmitLesson = () => {
        let newLesson = {
            'lessonName': lessonName,
            [lessonType === 'video' &&'lessonDuration']: lessonDuration,
            'lessonType': lessonType,
            'lessonContent': lessonType === 'article' ? lessonArticle : lessonType === 'video' ? uploadedFile : null 
         }

         setLessonList ((preList) => {
           return [...preList, newLesson]
            
         })

         setOpenLessonForm(false)
    }
    const handleFileEvent =  (e) => {
        const chosenFiles = Array.prototype.slice.call(e.target.files)

        setUploadedFile(chosenFiles[0]);
    }

    const submitModule = async () => {
        const modulePayload = {
            "title": moduleTitle, "canViewed": false
        }
        
        let res = await createModule(modulePayload, courseID)
        console.log(res);

        
    }
    console.log(courseID)

    return ( <section>
        <SectionTitle title={'Edit Course'}></SectionTitle>

        <form className="mt-10 space-y-10 text-headline-21">
                <div className='space-y-4'>
                <div className="flex justify-between">
                    <p >Course Name: <span className="font-bold">Python for Beginners</span> </p>
                    <p className={"cursor-pointer " +(!openEditCourseName ? "text-primary-500 hover:text-primary-900" : "text-error-500 hover:text-error-900")}  onClick={()=>{setOpenEditCourseName(pre => !pre)}}>{openEditCourseName? "Discard Change" : "Edit"}</p>
                </div>

                <input type="text" name="editCourseName" id="editCourseName" className={(openEditCourseName ? 'block' : 'hidden') + ' w-full p-2 border border-black'} placeholder='New Course Name' />
            </div>

            <div className='space-y-2'>
                <div className="flex justify-between">
                    <p >Course Description</p>
                    <p className={"cursor-pointer " +(!openEditCourseDescription ? "text-primary-500 hover:text-primary-900" : "text-error-500 hover:text-error-900")}  onClick={()=>{setOpenEditCourseDescription(pre => !pre)}}>{openEditCourseDescription? "Discard Change" : "Edit"}</p>
                    
                </div>
                <p className="font-bold">Old Description Text</p>
                <textarea  name="courseDescription" id="courseDescription" className={(openEditCourseDescription ? 'block': 'hidden')+' block w-full p-2 border border-black'} rows="5"  />
            </div>

            <div className='space-y-2'>
                <div className="flex justify-between">
                    <p>List Modules</p>
                    <p className="cursor-pointer text-primary-500" onClick={() => {setOpenModal(true)}}>Add a new module</p>

                    <Modal
                        show={openModal}
                        size="4xl"
                        popup={true}
                        onClose={onClose}
                    >
                        <Modal.Header />
                        <Modal.Body>
                        <div className="px-6 pb-4 space-y-6 sm:pb-6 lg:px-8 xl:pb-8">
                            <h2 className="font-bold text-primary-500 text-headline-31 ">
                                New Module 
                            </h2>
                            
                            <div className="space-y-5">
                                <div className='space-y-2'>
                                    <label htmlFor="moduleName" className="font-medium" >Module Name</label>
                                    <input type="text" onChange={(e) => {setModuleTitle(e.target.value) }} name="moduleName" id="moduleName" className='block w-full p-2 border border-black' placeholder='Setting Up Python' />
                                </div>

                                <div className='space-y-2'>
                                    <label  className="block font-medium" >List Lesson</label>
                                    <div>
                                        {lessonList.map(l => 
                                        
                                            <div className="flex items-center justify-between">
                                                <div className="flex items-center gap-1">
                                                    <span>{l.lessonName}</span>
                                                   <span className="italic font-light text-small-16">{l.lessonType}</span> 

                                                </div>

                                                <p>{l.lessonDuration && (l.lessonDuration + 's')}</p>
                                            </div>
                                        )}
                                    </div>
                                </div>

                                {/* Add Lesson Section */}

                                {
                                    openLessonForm ? 
                                    <div className="px-6 py-3 space-y-2 border border-grey-500">

                                        <h4 className="font-medium text-lead-24">Add a Lesson</h4>
                                        
                                        <div className='space-y-1'>
                                            <label htmlFor="lessonName" >Lesson Name</label>
                                            <input type="text" name="lessonName" onChange={(e) => {setLessonName(e.target.value)}} id="lessonName" className='block w-full p-2 border border-black' placeholder='Lesson 1' />
                                        </div>


                                        <div className='space-y-1'>
                                            <p >Lesson Content Type</p>

                                            <label className="block" htmlFor="article"><input type="radio" selected id="article" name="lessonType" onChange={(e) => {setLessonType(e.target.value)}} value={'article'}/> Article</label>
                                            <label className="block" htmlFor="video"><input type="radio" id="video" name="lessonType" onChange={(e) => {setLessonType(e.target.value)}} value={'video'}/> Video</label>
                                
                                        </div>


                                        {
                                            lessonType === 'article' ? 
                                            <div className='space-y-1'>
                                                <label htmlFor="article" >Article Content</label>
                                                <textarea  name="article" onChange={(e) => {setLessonArticle(e.target.value)}} id="article" className='block w-full p-2 border border-black' rows="5"  />
                                            </div>
                                            : lessonType === 'video' ?
                                            <>
                                                <div className='space-y-1'>
                                                    <label htmlFor="courseVideoUpload">Upload A Video </label>
                                                    <input id='fileUpload' type='file'
                                                
                                                    onChange={handleFileEvent}
                                                    
                                                    />

                                                    <div className="space-y-1 italic font-light text-success-500 text-body-18">
                                                        {uploadedFile.name}
                                                    </div>
                                            
                                                </div>


                                                <div className='space-y-1'>
                                                    <label htmlFor="lessonDuration" >Lesson Duration (in seconds)</label>
                                                    <input type="text" name="lessonDuration" onChange={(e) => {setLessonDuration(e.target.value)}} id="lessonDuration" className='block w-full p-2 border border-black' placeholder='120' />
                                                </div>

                                            </>
                                            
                                            : null

                                        }

                                        <div className="flex items-center gap-6 pt-6">
                                            <Button isPrimary={false} onClick={handleSubmitLesson}  size='small' text={'Add Lesson'}></Button>
                                            <p onClick={() => {setOpenLessonForm(false)}} className="font-medium cursor-pointer text-error-500 ">Discard</p>
                                            
                                        </div>
                                    </div>
                                    :
                                    <p className="underline cursor-pointer text-primary-500" onClick={() => {setOpenLessonForm(true)}}>Add A Lesson</p>
                                }
                                
           
                                <div className="flex justify-end pt-4">
                                    <Button isPrimary={true} size="large" onClick={submitModule}  text={"Add Module"}></Button>
                                </div>
                                
                                
                            </div>
                            
                        </div>
                        </Modal.Body>
                    </Modal>

                </div>
                <Accordion>
                    <Accordion.Panel>
                        <Accordion.Title className={" text-button-21"}>
                        Module Title
                        </Accordion.Title>
                        <Accordion.Content>
                        <p className="mb-2 text-gray-500 ">
                            <CourseContent contentTitle={'String'} duration='6:05'/>
                            <CourseContent contentTitle={'Variables'} duration='6:05'/>
                        </p>
                        
                        </Accordion.Content>
                    </Accordion.Panel>
                </Accordion>
               
            </div>

            <Button isPrimary={true} size="large" className={'mb-2'} text={"Save Edit"}></Button>
           
        </form>
    </section> );
}
 
export default CourseEdit;