import axios from "axios";
import { React, useState } from "react";
import { useNavigate } from "react-router-dom";
import "tailwindcss";

const Login = () => {
  const [userPhone, setUserPhone] = useState();
  const [userPassword, setUserPassword] = useState();
  const [message, setMessage] = useState();
  const navigate = useNavigate();

  function messageDisplay(message) {
    document.getElementById("message").style.color = "red";
    setMessage(message);
  }
  function hasWhiteSpace(s) {
    return s.indexOf(" ") >= 0;
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
      if (hasWhiteSpace(response.data)) {
        messageDisplay(response.data);
        return;
      } else {
        console.log(response.data);
        localStorage.setItem("jwtToken", response.data);
        navigate("/");
      }
    } catch (error) {
      console.log(error);
      messageDisplay("Error occurred during login");
    }
  };

  return (
    <>
      <div className="flex flex-column align-center justify-center h-screen">
        <div
          className="border-2"
          style={{
            borderColor : "#fcd34d",
            height: "200px",
            width: "300px",
            paddingRight: "20px",
            paddingLeft: "20px",
            paddingBottom: "20px",
            marginTop: "350px",
          }}
        >
          <div><h3>Login</h3></div>
          <form action="" onSubmit={loginUser} method="post">
            <div id="message" className="text-red-500">
              <p>{message}</p>
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="userPhone">Phone Number</label>
              <input
                value={userPhone}
                placeholder="Enter Phone number"
                onChange={(e) => setUserPhone(e.target.value)}
                type="number"
                id="userPhone"
              />
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Role</label>
              <select name="" id="">
                <option value="EMPLOYEE">EMPLOYEE</option>
                <option value="EMPLOYER">EMPLOYER</option>
              </select>
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="userPassword">Password</label>
              <input
                value={userPassword}
                placeholder="Enter password"
                onChange={(e) => setUserPassword(e.target.value)}
                type="password"
                id="userPassword"
              />
            </div>
            <div className="flex flex-row align-center justify-center">
            <button style={{backgroundColor : "#fcd34d",borderColor :"#fcd34d",borderRadius : "5px",height : "30px", width : "100px",marginTop : "5px"}} type="submit">Login</button>
            </div>
            <div
              className="flex flex-row align-center justify-center"
              style={{ marginTop: "10px" }}
            >
              Don't have an account ? <a href="/register"> Register</a>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default Login;
