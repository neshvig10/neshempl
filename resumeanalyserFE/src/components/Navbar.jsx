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
            <nav className="top-0 fixed w-full bg-amber-100 text-amber-300 h-12 pt-3">
                <div className="flex flex-row justify-between">
                    <div>
                        <a className="text-amber-200 decoration-amber-200 m-2.5" href="/">neshempl</a>
                        {localStorage.getItem("userRole") == "EMPLOYER" ?
                        <a className="text-amber-200 decoration-amber-200 m-2.5" href="/postjob">Post Job opening</a>
                        :
                        <a className="text-amber-200 decoration-amber-200 m-2.5" href="/uploadresume">Upload Resume</a>
                        }
                    </div>

                    <div>
                        {
                        user!=null ?
                        <div>
                            <a className="text-amber-200 decoration-amber-200 m-2.5" href="" onClick={logout()}>Logout</a>
                            <a className="text-amber-200 decoration-amber-200 m-2.5" href="/users/:userId">Profile</a>
                        </div>
                        :
                   <div>
                            <a className="text-amber-200 decoration-amber-200 m-2.5" href="/login">Login</a>
                            <a className="text-amber-200 decoration-amber-200 m-2.5" href="/register">Register</a>
                        </div>

                        }
                    </div>
                </div>
            </nav>
        </>
    )

}

export default Navbar;