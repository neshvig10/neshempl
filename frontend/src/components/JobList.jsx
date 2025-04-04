import { React} from "react";
import Job from "./Job";

const JobList = (props) => {
  return (
    <>
      <div
        style={{overflow:"auto",height : "600px",width: "100%"}}
      >
        {" "}
        {props.jobs.map((job) => (
          <Job
            key={job.jobId}
            title={job.jobTitle}
            company={job.companyName}
            location={job.locations}
            experience={job.experienceRequired}
            onClick={props.setJobIndex(job.jobId)}
          ></Job>
        ))}
      </div>
    </>
  );
};

export default JobList;
