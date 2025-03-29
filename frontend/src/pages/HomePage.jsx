import {React} from "react";

const HomePage = ()=> {
    return (
        <>  
        <div className="flex flex-col" style={{ "paddingTop" : "70px"}}>
            <div>Search Bar</div>
            <div className="flex flex-row">
                <div>List Of Jobs</div>
                <div>Job Description</div>
            </div>
        </div>

        </>
    )
}

export default HomePage;