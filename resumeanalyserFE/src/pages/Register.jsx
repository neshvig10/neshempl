import axios from 'axios';
import {React, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';


const Register = () => {

    const [name,setName] = useState('');
    const [role,setRole] = useState("EMPLOYEE");
    const [phone,setPhone] = useState();
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState('');
    const [confirmPassword,setConfirmPassword] = useState('');
    const [message,setMessage] = useState("");
    const navigate = useNavigate();


    function messageDisplay(message){
        setMessage(message)
        document.getElementById("message").style.color="red";

    }

    const  registerUser = async (e) => {

        e.preventDefault();

        setRole(document.getElementById("role").value);

        if (password !== confirmPassword){
            messageDisplay("Passwords don't match");
            return;
        }
        else{
            setMessage("")
            document.getElementById("message").style.color="black";
        }

        const user = {
            "userName" : name,
            "userPhone" : phone,
            "userEmail" : email,
            "userPassword" : password,
            "userRoles" : role
        }
        try{
            const response = await axios.post('http://localhost:8080/usermanagement/api/auth/register'+'?'+'userName='+name+'&userPhone='+phone+'&userEmail='+email+'&userPassword='+password+'&userRoles='+role,user);
            console.log(response);
            console.log(response.status);
            if(response.status === 200){

                if (response.data !== "Registered"){
                    messageDisplay(response.data);
                }
                else{
                    navigate('/login');
                }
            }
            else{
                setMessage(response.data);
            } 
        }
        catch(error){
            console.error("Error registering user",error);
        }
    }

    return (
        <>
            <div className='border-2 w-[300px] mx-[700px] my-[300px] py-5 px-5'> 
            <div>

            <h3 className='text-2xl font-bold'>
                neshempl 
            </h3>

            </div>
            <div id='message'>
                    <p>{message}</p>

            </div>
            <div>

            <form onSubmit={registerUser}> 
                <div>
                        <label>
                                Name 
                        </label> 
                        <input className='border-2' type="text" value={name} onChange={(e)=> setName(e.target.value)} />
                </div>

                <div>
                    <label>Role</label>
                    <br />
                    <select name="role" id="role">
                      <option value="EMPLOYEE">EMPLOYEE</option>
                      <option value="EMPLOYER">EMPLOYER</option>
                    </select>
                  </div>

                <div>
                    <label>
                        Phone 
                    </label> 
                    <input className='border-2' type="number" value={phone} onChange={(e)=> setPhone(e.target.value)} />
                </div>
                <div>
                    <label>
                        Email 
                    </label> 
                    <input className='border-2' type="email" value={email} onChange={(e)=> setEmail(e.target.value)} />
                </div>
                <div>
                    <label>
                        Password 
                    </label> 
                    <input className='border-2' type="password" value={password} onChange={(e)=> setPassword(e.target.value)} />
                </div>
                <div>
                    <label>
                        Confirm Password 
                    </label> 
                    <input className='border-2' type="password" value={confirmPassword} onChange={(e)=> setConfirmPassword(e.target.value)} />
                </div>
                <button
                    type="submit"
                    className="bg-amber-500 hover:bg-amber-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline m-2 mx-20"
                >
                    Register
                </button>
                </form>
            </div>
                <div>
                    Already have an account ?
                    <Link className='text-amber-200 underline decoration-amber-200' to="/login"> Login</Link>
                </div>
            </div>
        </>
    )
}

export default Register;
