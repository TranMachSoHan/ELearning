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
import TeacherLayout from "./layouts/TeacherLayout";
import OAuth2RedirectHandler from "./Utils/OAuth2/OAuth2RedirectHandler";
import { useState } from "react";
import { getCurrentUser } from "./Utils/APIUltils";
import { ACCESS_TOKEN } from "./constants";

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
        <Route path="/skill" element={<CourseBySkill />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/register" element={<SignUp />}></Route>
        <Route path="/courseDetail" element={<CourseDetail />}></Route>
        <Route path="/studentDetail" element={<StudentDetail />}></Route>
        <Route path="/learning" element={<Learning />}></Route>
        <Route path="/oauth2/redirect" element={OAuth2RedirectHandler}></Route>
      </Route>

      <Route element={<TeacherLayout />}>
        <Route path="/teacher" element={<TeacherDashboard />}></Route>
      </Route>
    </Routes>
  );
}

export default App;
