
import { Route, Routes, Switch } from 'react-router-dom';
import CourseBySkill from './pages/CourseBySkill';
import CourseDetail from './pages/CourseDetail';

import Home from './pages/Home';
import Learning from './pages/Learning';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import StudentDetail from './pages/StudentDetail';
import TeacherDashboard from './pages/TeacherDashboard';
import StudentLayout from './layouts/StudentLayout'
import TeacherLayout from './layouts/TeacherLayout'
import CourseEdit from './pages/CourseEdit';

function App() {
  return (

    <Routes>

        <Route element={<StudentLayout/>}>
          <Route path='/' element={<Home/>} ></Route>
          <Route path='/skill/:skillName' element={<CourseBySkill/>} ></Route>

          <Route path='/login' element={<Login/>} ></Route>
          <Route path='/register' element={<SignUp/>} ></Route>
          <Route path='/courseDetail/:courseID' element={<CourseDetail/>} ></Route>
          <Route path='/studentDetail' element={<StudentDetail/>} ></Route>
          <Route path='/learning' element={<Learning/>} ></Route>
        </Route>
        
        <Route element={<TeacherLayout/>}>
          <Route path='/teacher' element={<TeacherDashboard/>}></Route>
          <Route path='/edit-course' element={<CourseEdit/>}></Route>
        </Route>

      
    </Routes>

    
  );
}

export default App;