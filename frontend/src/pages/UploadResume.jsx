import {React} from "react";

const UploadResume = ()=>{
    return (
        <>
            <div className="flex flex-col justify-around" style={{marginTop : "20px",paddingTop : "200px"}}>
                <label htmlFor="">Upload your Resume</label>
                <input accept="application/pdf" type="file"/>
            </div>
            
        </>
    )
}


export default UploadResume;