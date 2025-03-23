import axios from "axios";
import { React, useState } from "react";
import "tailwindcss";

const Login = () => {
  const [userPhone, setUserPhone] = useState();
  const [userPassword, setUserPassword] = useState();
  const [message, setMessage] = useState();

  function messageDisplay(message){
    document.getElementById("message").style.color="red";
    setMessage(message);
    
  }
  function hasWhiteSpace(s) {
    return s.indexOf(' ') >= 0;
  }

  const loginUser = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/usermanagement/api/auth/login?" +
          "userPhone=" +
          userPhone +
          "&userPassword=" +
          userPassword
      );
      console.log(response);
      if (hasWhiteSpace(response.data)){
        messageDisplay(response.data);
        return;
      }
      else{
        console.log(response.data);
        localStorage.setItem("jwtToken",response.data);
      }

    } catch (error) {
        console.log(error);
        messageDisplay("Error occurred during login");   
    }
  };

  return (
    <>
      <div className="border-1 absolute mx-10">

        <form action="" onSubmit={loginUser} method="post">

        <div id="message" className="text-red-500"><p>{message}</p></div>
        <div className="flex flex-row justify-between">
          <label htmlFor="userPhone">Phone Number</label>
          <input
            value={userPhone}
            onChange={(e) => setUserPhone(e.target.value)}
            type="number"
            id="userPhone"
          />
        </div>
        <div className="flex flex-row justify-between">
          <label htmlFor="userPassword">Password</label>
          <input
            value={userPassword}
            onChange={(e) => setUserPassword(e.target.value)}
            type="password"
            id="userPassword"
          />
        </div>
        <button type="submit">Login</button>
        </form>
        Don't have an account ? <a href="/register">Register</a>
      </div>
    </>
  );
};

export default Login;
