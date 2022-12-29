import { Accordion, Modal } from "flowbite-react";
import { useState } from "react";
import Button from "../components/Button";
import CourseContent from "../components/CourseContent";
import SectionTitle from "../components/SectionTitle";

const CourseEdit = () => {

     const [contentType, setContentType] = useState(null);

    const [openEditCourseName, setOpenEditCourseName] = useState(false)
    const [openEditCourseDescription, setOpenEditCourseDescription] = useState(false)

    const [uploadedFiles, setUploadedFiles] = useState([])


    const [openModal, setOpenModal] = useState(false)

    const onClose = () => {
        setOpenModal(false)
    }
    const handleUploadFiles = files => {
        const uploaded = [...uploadedFiles, ...files];

        setUploadedFiles(uploaded);
        
    }
    const handleFileEvent =  (e) => {
        const chosenFiles = Array.prototype.slice.call(e.target.files)
        handleUploadFiles(chosenFiles);
    }
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
                        size="3xl"
                        popup={true}
                        onClose={onClose}
                    >
                        <Modal.Header />
                        <Modal.Body>
                        <div className="px-6 pb-4 space-y-6 sm:pb-6 lg:px-8 xl:pb-8">
                            <h2 className="font-bold text-gray-900 text-headline-31 ">
                                Add Module Content
                            </h2>
                            
                            <form className="space-y-5">
                                <div className='space-y-2'>
                                    <label htmlFor="moduleName" >Module Name</label>
                                    <input type="text" name="moduleName" id="moduleName" className='block w-full p-2 border border-black' placeholder='Python for Beginners' />
                                </div>

                                <div className='space-y-2'>
                                    <p >Module Content Type</p>

                                     <label className="block" htmlFor="reading"><input type="radio" selected id="reading" name="contentType" onChange={(e) => {setContentType(e.target.value)}} value={'reading'}/> Reading</label>
                                    <label className="block" htmlFor="video"><input type="radio" id="video" name="contentType" onChange={(e) => {setContentType(e.target.value)}} value={'video'}/> Video</label>
                        
                                </div>


                                {
                                    contentType === 'reading' ? 
                                    <div className='space-y-2'>
                                        <label htmlFor="courseDescription" >Reading Content</label>
                                        <textarea  name="courseDescription" id="courseDescription" className='block w-full p-2 border border-black' rows="5"  />
                                    </div>
                                    : contentType === 'video' ?
                                    
                                    <div className='space-y-2'>
                                        <label htmlFor="courseVideoUpload">Upload Videos </label>
                                        <input id='fileUpload' type='file' multiple
                                       
                                        onChange={handleFileEvent}
                                        
                                        />

                                        <div className="space-y-1 italic font-light text-body-18">
                                            {uploadedFiles.map(file => (
                                                <div >
                                                    {file.name}
                                                </div>
                                            ))}
                                        </div>
                                       
                                    </div>
                                    : null

                                }

                                

                                <Button isPrimary={true} size="large" className={'mb-2'} text={"Add"}></Button>
                                
                            </form>
                            
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