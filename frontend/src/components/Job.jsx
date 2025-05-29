import axios from "axios";
import { React, useContext } from "react";
import { DescriptionContext } from "../contexts/DescriptionContext";

const Job = (props) => {
  console.log("JobComponentprops", props);

  const {setJobIndex} = useContext(DescriptionContext);

  const deleteJob = async () => {
    const response = await axios.delete(
      "http://localhost:8080/api/job/deletejob?jobId=" + props.jobId
    );
    console.log(response);
    window.location.reload();
  };

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
        <div className="flex flex-row justify-between">
          <div>
            {" "}
            <h3 style={{ paddingLeft: "4px", color: " #BB6A00C2" }}>
              {props.title}
            </h3>
            <p style={{ paddingLeft: "4px" }}>{props.company}</p>
          </div>
          <div className="flex flex-col justify-around">
            {props.userRole === "EMPLOYER" ? (
              <a
                style={{ textDecoration: "none", color: "#D37800C2" }}
                href={`/job/${props.jobId}/applicants`}
              >
                <h3>Applicants</h3>
              </a>
            ) : (
              <p>Exp Reqd.: {props.experience} yrs.</p>
            )}

            {/* <p style={{marginRight : "5px"}}>
              {props.location
                .map((item) => item.location.locationName.trim())
                .join(", ")}
            </p> */}
            {localStorage.getItem("userRoleAuth") !== "EMPLOYER" ? (
              <button onClick={() => {setJobIndex(props.jobIndex)}} style={{ marginRight: "10px", marginBottom: "5px" }}>
                Check details
              </button>
            ) : (
              <></>
            )}

            {localStorage.getItem("userRoleAuth") === "EMPLOYER" ? (
              <img
                src="src/assets/delete.png"
                onClick={deleteJob}
                style={{
                  width: "20px",
                  marginRight: "20px",
                  marginTop: "10px",
                  marginBottom: "10px",
                  marginLeft: "80px",
                  paddingRight: "0px",
                }}
              ></img>
            ) : (
              <></>
            )}
          </div>
        </div>
      </div>
    </>
  );
};

export default Job;
