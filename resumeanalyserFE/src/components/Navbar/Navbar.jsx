import {React} from 'react'
import './Navbar.css'


const Navbar = ()=>{
    return(
        <>  
        <nav>
            <div>
            <a href="">NeshEmpl</a>
            <a href="/uploadresume">UploadResume</a>
            </div>
            <div>
                Search Bar
            </div>
            <div>
            <a href="">Login</a>
            <a href="">Register</a>
            </div>
            <div>
                <a href="">Profile</a>
            </div>


        </nav>
        </>
    )
}

export default Navbar;