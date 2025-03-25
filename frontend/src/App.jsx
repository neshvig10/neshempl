import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import Register from "./pages/Register";
import UploadResume from "./pages/UploadResume";
import HomePage from "./pages/HomePage";
import PostJob from "./pages/PostJob";
import { useContext } from "react";
import { AuthContext } from "./contexts/AuthContext";

function App() {
  const { isLoggedIn } = useContext(AuthContext);

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
            element={<UploadResume></UploadResume>}
          ></Route>
          <Route path="/postjob" element={<PostJob></PostJob>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
