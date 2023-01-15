import "./App.css";
import SideBar from "./components/SideBar";
import { Route, Routes } from "react-router-dom";
import { Stats } from "./pages/Stats";
import { Courses } from "./pages/Courses";
import { Career } from "./pages/Career";
import { Login } from "./components/Login";
import { useState } from "react";

function App() {
  const [token, setToken] = useState();
  if (!token) {
    return <Login setToken={setToken} />;
  }
  return (
    <div class="flex flex-row">
      <SideBar></SideBar>
      <Routes>
        <Route path="/stats" element={<Stats />}></Route>
        <Route path="/course" element={<Courses />}></Route>
        <Route path="/career" element={<Career />}></Route>
      </Routes>
    </div>
  );
}

export default App;
