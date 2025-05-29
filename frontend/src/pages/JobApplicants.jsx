import { React, useEffect, useState } from "react";
import JobDescription from "../components/JobDescription";
import axios from "axios";
import { useParams } from "react-router-dom";
import Applicant from "../components/Applicant";

const JobApplicants = () => {
  const { jobId } = useParams();
  const [applicants, setApplicants] = useState([]);
  const [job, setJob] = useState();

  const listOfApplicants = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/job/applicantsofjob" + "?jobId=" + jobId
    );
    setApplicants(response.data);
    console.log("list of applicants", response.data);
  };

  const getJobFromId = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/job/jobbyid" + "?jobId=" + jobId
    );
    setJob(response.data);
  };

  console.log(jobId, job, "applicants", applicants);

  useEffect(() => {
    listOfApplicants();
    getJobFromId();
  }, []);

  return (
    <>
      {job ? (
        <div
          style={{
            border: "solid",
            borderColor: "#FFEAACFF",
            backgroundColor: "#FFEAACFF",
            width: "",
            paddingTop: "60px",
          }}
          className="flex flex-row justify-between"
        >
          {/* {props.job[jobIndex]} */}
          <div className="flex flex-col justify-around">
            <h2>{job.jobTitle}</h2>
            <h3>{job.companyName}</h3>
            <p>{job.description}</p>
          </div>

          <div
            className="flex flex-col justify-between"
            style={{ margin: "20px" }}
          >
            <p>Salary : {job.salary} LPA</p>
          </div>
        </div>
      ) : (
        <div></div>
      )}
      <div style={{height : "full",paddingBottom : "15px",backgroundColor : "#FFDE7BFF"}}> 
        <h1>Applicants</h1>

        {applicants.length ?  (applicants.map((applicant) => (
          <Applicant
            key={applicants.indexOf(applicant)}
            applicant={applicant}
            jobId={jobId}
          ></Applicant>
          ))
        ) : (
            <>
            <div>
                No applicants
            </div>
            </>
        )
        }
      </div>
    </>
  );
};

export default JobApplicants;
