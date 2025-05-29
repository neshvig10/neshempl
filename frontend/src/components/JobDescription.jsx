import React, { useContext } from "react";
import Job from "./Job";
import { DescriptionContext } from "../contexts/DescriptionContext";
import axios from "axios";

const JobDescription = (props) => {
  console.log("jobDescription", props);
  const { jobIndex } = useContext(DescriptionContext);

  if (props.job) console.log("jobindexfromcontext", jobIndex);

  const applyToJob = async (jobId) => {
    // const response = await axios.post("http://localhost:8080/api/job/applytojob");
    // console.log(response.data);
    
  };

  return (
    <>
      {props.job.length ? (
        <div
          style={{ width: "", padding: "10px" }}
          className="flex flex-row justify-between"
        >
          {/* {props.job[jobIndex]} */}
          <div className="flex flex-col justify-around">
            <h2>{props.job[jobIndex].jobTitle}</h2>
            <h3>{props.job[jobIndex].companyName}</h3>
            <p>{props.job[jobIndex].description}</p>
          </div>

          <div
            className="flex flex-col justify-between"
            style={{ margin: "20px" }}
          >
            <p>Salary : {props.job[jobIndex].salary} LPA</p>
            <button
              onClick={() => {
                applyToJob(props.job[jobIndex].jobId);
              }}
            >
              Apply Now
            </button>
          </div>
        </div>
      ) : (
        <div>No Job selected</div>
      )}
    </>
  );
};

export default JobDescription;
