import axios from "axios";
import { React, useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [userName, setUserName] = useState();
  const [userPhone, setUserPhone] = useState();
  const [userEmail, setUserEmail] = useState();
  const [userPassword, setUserPassword] = useState();
  const [userConfirmPassword, setUserConfirmPassword] = useState();
  const [userRole,setUserRole] = useState("EMPLOYEE");
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
          userPassword +
          "&userRoles=" +
          userRole 
      );
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
      <div className="flex flex-column align-center justify-center h-screen">
        <div
          className="border-2"
          style={{
            borderColor : "#fcd34d",
            height: "270px",
            width: "320px",
            paddingRight: "20px",
            paddingLeft: "20px",
            paddingBottom: "20px",
            marginTop: "350px",
          }}
        >
          <div><h3>Registration</h3></div>
          <form action="" onSubmit={registerUser} method="post">
            <div id="message">
              <p>{message}</p>
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Name</label>
              <input
                placeholder = "Enter Name"
                value={userName}
                onChange={(e) => setUserName(e.target.value)}
                type="text"
              />
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Phone Number</label>
              <input
                placeholder = "Enter Phone number"
                value={userPhone}
                onChange={(e) => setUserPhone(e.target.value)}
                type="number"
              />
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Email</label>
              <input
                placeholder = "Enter e-mail"
                value={userEmail}
                onChange={(e) => setUserEmail(e.target.value)}
                type="email"
              />
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Role</label>
              <select name="role" id="userole" onChange={(e) => setUserRole(e.target.value)}>
                <option value="EMPLOYEE">EMPLOYEE</option>
                <option value="EMPLOYER">EMPLOYER</option>
              </select>
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Password</label>
              <input
                placeholder = "Enter Password"
                value={userPassword}
                onChange={(e) => setUserPassword(e.target.value)}
                type="password"
              />
            </div>
            <div
              className="flex flex-row justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Confirm Password</label>
              <input
                placeholder = "Re-enter Password"
                value={userConfirmPassword}
                onChange={(e) => setUserConfirmPassword(e.target.value)}
                type="password"
              />
            </div>
            <div className="flex flex-row align-center justify-center">
              <button style={{backgroundColor : "#fcd34d",borderColor :"#fcd34d",borderRadius : "5px",height : "30px", width : "100px",marginTop : "5px"}} type="submit">Register</button>
            </div>
          </form>
          <div
            className="flex flex-row align-center justify-center"
            style={{ marginTop: "10px" }}
          >
            Already have an account ? <a href="/login"> Login</a>
          </div>
        </div>
      </div>
    </>
  );
};

export default Register;
