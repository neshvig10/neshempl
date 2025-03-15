import Navbar from './components/Navbar.jsx'
import React from 'react';
import { BrowserRouter,Routes, Route } from "react-router-dom";
import Login from './pages/Login.jsx';
import { AuthProvider } from './contexts/AuthContext.jsx';
import Register from './pages/Register.jsx';


function App() {

  return (
    <>

      <BrowserRouter>
        <AuthProvider>
          <Navbar />
        </AuthProvider>
          <Routes>
            <Route path="/"/>
            <Route path="/login" element={<AuthProvider>
                                            <Login />
                                          </AuthProvider>} />
            <Route path="/register" element={<AuthProvider>
                                            <Register />
                                          </AuthProvider>} />
          </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
