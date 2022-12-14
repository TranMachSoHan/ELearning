
import { Route, Routes } from 'react-router-dom';
import CourseBySkill from './pages/CourseBySkill';
import CourseDetail from './pages/CourseDetail';

import Home from './pages/Home';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import StudentDetail from './pages/StudentDetail';

function App() {
  return (
    <Routes>

        <Route path='/' element={<Home/>} ></Route>
        <Route path='/skill' element={<CourseBySkill/>} ></Route>
        <Route path='/login' element={<Login/>} ></Route>
        <Route path='/register' element={<SignUp/>} ></Route>
        <Route path='/courseDetail' element={<CourseDetail/>} ></Route>
        <Route path='/studentDetail' element={<StudentDetail/>} ></Route>

      
    </Routes>
  );
}

export default App;