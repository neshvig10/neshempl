import { React } from "react";

const Job = (props) => {
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
            <p style={{paddingRight : "4px"}}>Experience</p>
          </div>
        </div>
      </div>
    </>
  );
};

export default Job;
