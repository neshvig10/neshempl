import { React } from "react";
import Job from "./Job";

const JobListEmployer = (props) => {

  console.log("jobemployerprops",props);
  
  return (
    <>
      <div style={{ overflow: "auto", height: "600px", width: "100%" }}>
        {" "}
        {props.jobs.map((job) => (
          <div key={job.jobId}>
            <Job
              key={job.jobId}
              jobId={job.jobId}
              title={job.jobTitle}
              company={job.companyName}
              location={job.locations}
              experience={job.experienceRequired}
              userRole={"EMPLOYER"}
            >
            </Job>
          </div>
        ))}
      </div>
    </>
  );
};

export default JobListEmployer;
