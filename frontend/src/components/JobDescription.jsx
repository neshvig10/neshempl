import { React } from "react";
import Job from "./Job";

const JobDescription = (props) => {
  return (
    <>
      <div
        style={{ width: "", padding: "10px" }}
        className="flex flex-row justify-between"
      >
        <div className="flex flex-col justify-around">
          <h2>Job Title</h2>
          <h3>Company Name</h3>
          <p>
            Job JobDescription Lorem ipsum dolor sit amet, consectetur
            adipisicing elit. Laudantium vitae rem velit, distinctio expedita
            sit iure, minima cupiditate consequatur debitis aperiam dolores.
            Voluptatum omnis voluptatibus nobis in mollitia, consequatur
            nostrum!
          </p>
          <button>Apply Now</button>
        </div>

        <div style={{ margin: "20px" }}>
          <p>Location(s)</p>
          <p>Salary</p>
        </div>
      </div>
    </>
  );
};

export default JobDescription;
