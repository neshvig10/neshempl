import { React} from "react";
import Job from "./Job";

const JobList = (props) => {
  return (
    <>
      <div
        style={{width: "650px"}}
      >
        {" "}
        {props.jobs.map((job) => (
          <Job
            key={job.jobId}
            title={job.jobTitle}
            company={job.companyName}
            location={job.locations}
          ></Job>
        ))}
      </div>
    </>
  );
};

export default JobList;
