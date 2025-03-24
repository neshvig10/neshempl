import {React} from "react";


const Navbar = ()=>{
    return (
        <>
            <div className="flex flex-row justify-between w-full fixed top-0 bg-amber-300" style={{ backgroundColor: '#fcd34d',height : '35px', paddingTop : '20px', paddingLeft : '5px',paddingRight : '5px'}}>
                <div>
                    <a className="m-3 text-amber-600 no-underline" href="">neshempl</a>
                    Role=="EMPLOYEE" ?
                    <a className="m-3 text-amber-600 no-underline" href="/uploadresume">Upload Resume</a>
                    :
                    <a className="m-3 text-amber-600 no-underline" href="/postjob">Post Job</a>
                </div>

                <div>
                    Search Bar
                </div>

                <div className="flex flex-row">

                    isLoggedIn ?
                    <div>
                        <a className="m-3 text-amber-600 no-underline" href="/login">Login</a>
                        <a className="m-3 text-amber-600 no-underline" href="/register">Register</a>
                    </div>
                    :
                    <div>
                        <a className="m-3 text-amber-600 no-underline" href="/logout">Logout</a>
                        <a className="m-3 text-amber-600 no-underline"href="/profile/{userId}">Profile</a>
                    </div>
                
                </div>

            </div>
        </>
    )
}

export default Navbar;