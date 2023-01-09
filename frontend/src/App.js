import { Route, Routes } from "react-router-dom";
import CourseBySkill from "./pages/CourseBySkill";
import CourseDetail from "./pages/CourseDetail";

import Home from "./pages/Home";
import Learning from "./pages/Learning";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import StudentDetail from "./pages/StudentDetail";
import TeacherDashboard from "./pages/TeacherDashboard";
import StudentLayout from "./layouts/StudentLayout";
import CourseEdit from './pages/CourseEdit'
import TeacherLayout from "./layouts/TeacherLayout";
import OAuth2RedirectHandler from "./Utils/OAuth2/OAuth2RedirectHandler";
import { useState } from "react";
import { getCurrentUser } from "./Utils/APIUltils";
import { ACCESS_TOKEN } from "./constants";
import TeacherDetail from "./pages/teacherDetail";

function App() {
  const [authenticated, setAuthenticated] = useState("false");
  const [currentUser, setcurrentUser] = useState({});
  const [loading, setLoading] = useState(true);

  const loadUser = () => {
    getCurrentUser()
      .then((response) => {
        setAuthenticated(true);
        setcurrentUser(response);
        setLoading(false);
      })
      .catch((err) => {
        setLoading(false);
      });
  };

  const handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    setAuthenticated(false);
    setcurrentUser({});
  };
  return (
    <Routes>
      <Route element={<StudentLayout />}>
        <Route path="/" element={<Home />}></Route>
        <Route path="/skill/:skillName" element={<CourseBySkill />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/register" element={<SignUp />}></Route>
        <Route path="/courseDetail/:courseID" element={<CourseDetail />}></Route>
        <Route path="/studentDetail/:studentID" element={<StudentDetail />}></Route>
        <Route path="/teacherDetail/:instructorID" element={<TeacherDetail />}></Route>
        <Route path="/learning/:courseName/:courseProgressID" element={<Learning />}></Route>
        <Route path="/edit-course" element={<CourseEdit/>}></Route>
        <Route
          path="/oauth2/redirect"
          element={<OAuth2RedirectHandler />}
        ></Route>
      </Route>

      <Route element={<TeacherLayout />}>
        <Route path="/teacher" element={<TeacherDashboard />}></Route>
      </Route>

    </Routes>
  );
}

export default App;
