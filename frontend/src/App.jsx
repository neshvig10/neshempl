import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Navbar from './components/Navbar'
import Login from './pages/Login'
import Register from './pages/Register'
import UploadResume from './pages/UploadResume'

function App() {

  return (
    <>
      <BrowserRouter>
      <Navbar></Navbar>
        <Routes>
          <Route path='/login' element={<Login></Login>}></Route>
          <Route path='/register' element={<Register></Register>}></Route>
          <Route path='/uploadresume' element={<UploadResume></UploadResume>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
