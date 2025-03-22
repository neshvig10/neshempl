import axios from "axios";
import "tailwindcss";
import { React, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";

const Login = () => {
  // const [userId, setUserId] = useState();
  const [userPhone, setuserPhone] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [role,setRole] = useState("EMPLOYEE");
  const navigate = useNavigate();

  const {login} = useAuth();


  const loginUser = async (e) => {
    e.preventDefault();

    setMessage("");
    const roleUser = document.getElementById("role").options[document.getElementById("role").selectedIndex].value

    setRole(roleUser);

    const user = {
      userPhone: userPhone,
      userPassword: password,
      userRole: role
    };

    try{
      const response = await axios.post(
        "http://localhost:8080/usermanagement/api/auth/login"+"?userPhone="+userPhone+"&userPassword="+password+"&userRole="+role,
        user,
        { headers: { Authorization: localStorage.getItem("SavedToken") } }
      );

      let token = response.data;
      console.log(response.data);
      console.log(userPhone,role);
      
      localStorage.setItem("jwtToken",token);
      localStorage.setItem("userPhone",userPhone);
      localStorage.setItem("userRole",role);

      login(user);
      //setTimeout(() => {
      navigate("/");

      //}, 2000);
      //setTimeout(() => {
      window.location.reload();
      //}, 2000);

    }catch(err){
      if (err.response){
        if (err.response.status!==200){
          setMessage(err.response.data);
        }
      }
      else{
        setMessage("Error occured, try again");
      }
    }


  };

  return (
    <>
          <div className="border-2 w-[300px] mx-[700px] my-[300px] py-5 px-5">
              <div>
                <h3 className="text-2xl font-bold">neshempl</h3>
              </div>

              <div>
                <p className="text-red-400">{message}</p>
              </div>

              <div>
                <form onSubmit={loginUser}>

                <div>
                    <label>Role</label>
                    <br />
                    <select name="role" id="role">
                      <option value="EMPLOYEE">EMPLOYEE</option>
                      <option value="EMPLOYER">EMPLOYER</option>
                    </select>
                  </div>

                  
                  <div>
                    <label>Phone Number</label>
                    <input
                      className="border-2"
                      type="text"
                      value={userPhone}
                      onChange={(e) => setuserPhone(e.target.value)}
                    />
                  </div>

                  <div>
                    <label>Password</label>
                    <input
                      className="border-2"
                      type="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>


                  <button
                    type="submit"
                    className="bg-amber-500 hover:bg-amber-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline m-2 mx-20"
                    onClick={() => (true)}
                  >
                    Login
                  </button>
                </form>
              </div>

              <div>
                Don't have an account ?
                <Link className="text-amber-400 underline" to="/register">
                  Register
                </Link>
              </div>
            </div>
    </>
  );
};

export default Login;