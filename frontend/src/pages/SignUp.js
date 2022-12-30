import { useState } from "react";
import Button from "../components/Button";
import SectionTitle from "../components/SectionTitle";

const SignUp = () => {

    const [registerType, setRegisterType] = useState(null);
    return ( <section className="min-h-[700px] pt-24">

        <SectionTitle title='Sign Up'></SectionTitle>

        <form className='w-1/2 pt-20 mx-auto space-y-6'>
            <div className='space-y-2'>
                <label htmlFor="name" >Name</label>
                <input type="text" name="name" id="name" className='block w-full p-2 border border-black' placeholder='John Doe' />
            </div>

            <div className='space-y-2'>
                <label htmlFor="email" >Email</label>
                <input type="text" name="email" id="email" className='block w-full p-2 border border-black' placeholder='email@gmail.com' />
            </div>
            <div className='space-y-2'>
                <label htmlFor="password" >Password</label>
                <input type="password" name="password" id="password" className='block w-full p-2 border border-black'  />
            </div>

            <div className='space-y-2'>
                <p>Register as</p>
                <label className="block" htmlFor="student"><input type="radio" selected id="student" name="registerType" onChange={(e) => {setRegisterType(e.target.value)}} value={'student'}/> Student</label>
                <label className="block" htmlFor="instructor"><input type="radio" id="instructor" name="registerType" onChange={(e) => {setRegisterType(e.target.value)}} value={'instructor'}/> Instructor</label>

            </div>

            {
                registerType === 'student' ? 
                <>
                    <div className='space-y-2'>
                        <label htmlFor="major" >Major</label>

                        <select name="major" className='block w-full p-2 border border-black' id="major">
                             <option disabled selected></option>
                            <option value="Software Engineering">Software Engineering</option>
                            <option value="Digital Marketing">Digital Marketing</option>
                            <option value="Business Management">Business Management</option>
                            <option value="IT">IT</option>
                            
                        </select>
               
                    </div>
                    <div className='space-y-2'>
                        <label htmlFor="minor" >Minor (Optional)</label>

                        <select name="major" className='block w-full p-2 border border-black' id="major">
                            <option disabled selected></option>
                            <option value="Software Engineering">Software Engineering</option>
                            <option value="Digital Marketing">Digital Marketing</option>
                            <option value="Business Management">Business Management</option>
                            <option value="IT">IT</option>
                            
                        </select>
                
                    </div>
                </>
                
                :   registerType === 'instructor' ? 
                <>
                    <div className='space-y-2'>
                        <label htmlFor="brief" >Brief Introduction About Yourself (background, experience, ...etc)</label>
                        <textarea  name="brief" id="brief" className='block w-full p-2 border border-black' rows="10"  />
                    </div>
                
                </> : null
            }

            <Button text={'Sign Up'} isPrimary={true}></Button>
        </form>
    </section>  );
}
 
export default SignUp;