import SectionTitle from "../components/SectionTitle";
import Button from "../components/Button";

const Login = () => {
    return ( <section className="min-h-[700px] pt-24">

        <SectionTitle title='Login'></SectionTitle>

        <form className='w-1/4 pt-20 mx-auto space-y-6'>
            <div className='space-y-2'>
                <label htmlFor="email" >Email</label>
                <input type="text" name="email" id="email" className='block w-full p-2 border border-black' placeholder='email@gmail.com' />
            </div>
            <div className='space-y-2'>
                <label htmlFor="password" >Password</label>
                <input type="password" name="password" id="password" className='block w-full p-2 border border-black'  />
            </div>

            <Button text={'Login'} isPrimary={true}></Button>
        </form>
    </section> );
}
 
export default Login;