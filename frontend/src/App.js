
import { Route, Routes } from 'react-router-dom';
import CourseBySkill from './pages/CourseBySkill';

import Home from './pages/Home';

function App() {
  return (
    <Routes>

        <Route path='/' element={<Home/>} ></Route>
        <Route path='/skill' element={<CourseBySkill/>} ></Route>

      
    </Routes>
  );
}

export default App;