import axios from "axios";
import { React, useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [userName, setUserName] = useState();
  const [userPhone, setUserPhone] = useState();
  const [userEmail, setUserEmail] = useState();
  const [userPassword, setUserPassword] = useState();
  const [userConfirmPassword, setUserConfirmPassword] = useState();
  const navigate = useNavigate();

  const [message, setMessage] = useState();

  function messageDisplay(message) {
    setMessage(message);
    document.getElementById("message").style.color = "red";
  }

  const registerUser = async (e) => {
    e.preventDefault();

    if (userPassword !== userConfirmPassword) {
      messageDisplay("Passwords don't match");
      return;
    } else {
      setMessage("");
      document.getElementById("message").style.color = "black";
    }
    try {
        const response = await axios.post(
            "http://localhost:8080/usermanagement/api/auth/register" +
              "?userName=" +
              userName +
              "&userPhone=" +
              userPhone +
              "&userEmail=" +
              userEmail +
              "&userPassword=" +
              userPassword
          )
          if (response.data === "Registered") {
            console.log("Registered");
            navigate("/login");
          } else {
            messageDisplay(response.data);
          }
        
    } catch (error) {
        console.log(error);
        messageDisplay("Error Occurred while registering");
        
    }
    
  };

  return (
    <>
      <div className="border-1">
        <form action="" onSubmit={registerUser} method="post">
          <div id="message">
            <p>{message}</p>
          </div>
          <div className="flex flex-row justify-between">
            <label htmlFor="">Name</label>
            <input
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              type="text"
            />
          </div>
          <div className="flex flex-row justify-between">
            <label htmlFor="">Phone Number</label>
            <input
              value={userPhone}
              onChange={(e) => setUserPhone(e.target.value)}
              type="number"
            />
          </div>
          <div className="flex flex-row justify-between">
            <label htmlFor="">Email</label>
            <input
              value={userEmail}
              onChange={(e) => setUserEmail(e.target.value)}
              type="email"
            />
          </div>
          <div className="flex flex-row justify-between">
            <label htmlFor="">Password</label>
            <input
              value={userPassword}
              onChange={(e) => setUserPassword(e.target.value)}
              type="password"
            />
          </div>
          <div className="flex flex-row justify-between">
            <label htmlFor="">Confirm Password</label>
            <input
              value={userConfirmPassword}
              onChange={(e) => setUserConfirmPassword(e.target.value)}
              type="password"
            />
          </div>
          <button type="submit">Register</button>
        </form>
        Already have an account ? <a href="">Login</a>
      </div>
    </>
  );
};

export default Register;
