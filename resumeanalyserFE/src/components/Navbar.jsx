import {React} from "react";
import { useAuth } from "../contexts/AuthContext";


const Navbar = ()=> {

    const logout =()=>{
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("userPhone");
        localStorage.removeItem("userRole");
    }
    const {user} = useAuth();

    return(
        <>
            <nav className="top-0 fixed w-full bg-amber-700 text-amber-300 h-12 pt-3">
                <div className="flex flex-row justify-between">
                    <div>
                        <a className="text-amber-200 decoration-amber-200" href="/">neshempl</a>
                        {localStorage.getItem("userRole") == "EMPLOYER" ?
                        <a href="/postjob">Post Job opening</a>
                        :
                        <a href="/uploadresume">Upload Resume</a>
                        }
                    </div>

                    <div>
                        {
                        user!=null ?
                        <div>
                            <a href="" onClick={logout()}>Logout</a>
                            <a href="/users/:userId">Profile</a>
                        </div>
                        :
                   <div>
                            <a href="/login">Login</a>
                            <a href="/register">Register</a>
                        </div>

                        }
                    </div>
                </div>
            </nav>
        </>
    )

}

export default Navbar;