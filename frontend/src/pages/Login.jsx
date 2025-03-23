import axios from "axios";
import {React, useState} from "react";
import "tailwindcss";


const Login = ()=> {

    const [userPhone, setUserPhone] = useState();
    const [userPassword, setUserPassword] = useState();
    const [message,setMessage] = useState();

    function loginUser(){

        try {
            const jwtToken = axios.post("http://localhost:8080/usermanagement/api/auth/login?"+"userPhone="+userPhone+"&userPassword="+userPassword);
            console.log(jwtToken);
            
        
        } catch (error) {
            if (error.response){
                if (error.response.status===400){
                  setMessage("Phone number does not exists");
                }
                else if (error.response.status === 401){
                  setMessage("Password is wrong !");
                }
                else{
                  setMessage("Login failed, try again");
                }
              }
              else{
                setMessage("Error occured, try again");
              }
            
        }       
    }



    return (
        <>  
            <div className="border-1 absolute">
                <div value={message} className="text-red-500">
                </div>
                <div className="flex flex-row justify-between">
                    <label htmlFor="userPhone">Phone Number</label>
                    <input value={userPhone} onChange={(e)=> setUserPhone(e.target.value)} type="number" id="userPhone"/>
                </div>
                <div className="flex flex-row justify-between">
                    <label htmlFor="userPassword">Password</label>
                    <input value={userPassword} onChange={(e)=> setUserPassword(e.target.value)} type="password" id="userPassword" />
                </div>
                <button onClick={loginUser()}>Login</button>
                Don't have an account ? <a href="/register">Register</a>
            </div>
        </>
    )
}

export default Login;