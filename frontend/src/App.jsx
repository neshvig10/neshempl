import { BrowserRouter, Route, Routes, useParams } from "react-router-dom";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import Register from "./pages/Register";
import UploadResume from "./pages/UploadResume";
import HomePage from "./pages/HomePage";
import PostJob from "./pages/PostJob";
import { AuthContext } from "./contexts/AuthContext";
import { useEffect, useState } from "react";
import Profile from "./pages/Profile";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState();

  useEffect(() => {
    setIsLoggedIn(localStorage.getItem("isLoggedIn"));
  });

  return (
    <>
      <BrowserRouter>
        <Navbar></Navbar>
        <Routes>
          <Route path="/" element={<HomePage></HomePage>}></Route>
          <Route path="/login" element={<Login></Login>}></Route>
          <Route path="/register" element={<Register></Register>}></Route>
          <Route
            path="/uploadresume"
            element={
              !isLoggedIn ? <Login></Login> : <UploadResume></UploadResume>
            }
          ></Route>
          <Route
            path="/postjob"
            element={
              !isLoggedIn ? <Login></Login> : <PostJob></PostJob>
            }
          ></Route>
          <Route
          path="/profile/:userId"
          element={<ProfileComponent></ProfileComponent>}>
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

const ProfileComponent = ()=> {
  const {userId} = useParams();
  return (
    <>
    <Profile userId={userId}></Profile>
    </>
  )
}

export default App;
