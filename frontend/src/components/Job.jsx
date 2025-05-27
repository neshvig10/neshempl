import axios from "axios";
import { React } from "react";

const Job = (props) => {

  const deleteJob = async()=> {
    const response = await axios.delete("http://localhost:8080/api/job/deletejob?jobId="+props.jobId);
    console.log(response);
    window.location.reload(); 
  }

  return (
    <>
      <div
        style={{
          border: "1px solid",
          borderRadius: "10px",
          width: "600px",
          margin: "20px",
        }}
      >
        <div className = "flex flex-row justify-between">
          <div>
            {" "}
            <h3 style={{paddingLeft: "4px", color : " #BB6A00C2" }}>{props.title}</h3>
            <p style={{ paddingLeft: "4px" }}>{props.company}</p>
          </div>
          <div>
            {props.locations}
            <p style={{paddingRight : "4px"}}>Exp Reqd. {props.experience} yrs</p>
            {props.userRole === "EMPLOYER" ? <img src="src/assets/delete.png" onClick={deleteJob} style={{ width : "20px",marginTop : "10px", marginLeft : "80px",paddingRight : "0px"}}></img> : <></> }
          </div>
        </div>
      </div>
    </>
  );
};

export default Job;
