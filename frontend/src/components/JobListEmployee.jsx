import { React} from "react";
import Job from "./Job";

const JobListEmployee = (props) => {

  console.log("joblistEmployeeprops",props);
  

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
            location={job.jobLocations}
            experience={job.experienceRequired}
            description={job.description}
            skills={job.jobSkills}
            jobIndex={props.jobs.indexOf(job)}
          ></Job>
        ))}
      </div>
    </>
  );
};

export default JobListEmployee;
