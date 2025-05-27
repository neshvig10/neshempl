import axios from "axios";
import { React, useState } from "react";
import { useNavigate } from "react-router-dom";

const PostJob = () => {
  const [message, setMessage] = useState();
  const [jobTitle, setJobTitle] = useState();
  const [companyName, setCompanyName] = useState();
  const [experience, setExperience] = useState();
  const [jobSalary, setJobSalary] = useState();
  const [jobDescription, setJobDescription] = useState();
  const [jobLocation, setJobLocation] = useState();
  const [skillsRequired, setSkillsRequired] = useState();

  const navigate = useNavigate();

  const messageDisplay = (message) => {
    setMessage(message);
    document.getElementById("message").style.color = "red";
  };

  const postJob = async (e) => {
    
    e.preventDefault();

    const response = await axios.post(
      "http://localhost:8080/api/job/postjob?"
      + "jobTitle=" + jobTitle
      + "&companyName=" + companyName
      + "&skills=" + skillsRequired
      + "&locations=" + jobLocation
      + "&experienceRequired=" + experience
      + "&salary=" + jobSalary
      + "&description=" + jobDescription
      + "&postedUserId=" + localStorage.getItem("userId")
    );
    console.log(response.data);
    navigate("/")
    
  };

  return (
    <>
      <div className="flex flex-column align-center justify-center h-screen">
        <div
          className="border-2"
          style={{
            borderColor: "#fcd34d",
            height: "760px",
            width: "auto",
            paddingRight: "20px",
            paddingLeft: "20px",
            marginTop: "100px",
          }}
        >
          <div>
            <h2>Job Posting</h2>
          </div>
          <form action="" onSubmit={postJob} method="post">
            <div id="message">
              <p>{message}</p>
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Job Title
              </label>
              <input
                style={{ height: "30px", width: "600px", borderRadius: "5px" }}
                placeholder="Enter Job Title"
                value={jobTitle}
                onChange={(e) => setJobTitle(e.target.value)}
                type="text"
              />
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Company Name
              </label>
              <input
                style={{ height: "30px", width: "600px", borderRadius: "5px" }}
                placeholder="Enter Company Name"
                value={companyName}
                onChange={(e) => setCompanyName(e.target.value)}
                type="text"
              />
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Salary
              </label>
              <div className="flex flex-row">
                <input
                  style={{
                    height: "30px",
                    width: "500px",
                    borderRadius: "5px",
                  }}
                  placeholder="Enter Salary component"
                  value={jobSalary}
                  onChange={(e) => setJobSalary(e.target.value)}
                  type="number"
                />
                <select
                  style={{ marginLeft: "20px", borderRadius: "5px" }}
                  name=""
                  id="salarytype"
                >
                  <option value="LPA">LPA</option>
                  <option value="KPM">K.P.M.</option>
                </select>
              </div>
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Experience required (in years)
              </label>
              <input
                style={{ height: "30px", width: "600px", borderRadius: "5px" }}
                placeholder="Enter experience reqiured"
                value={experience}
                onChange={(e) => setExperience(e.target.value)}
                type="number"
              />
            </div>

            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Skill(s) required
              </label>
              <input
                style={{ height: "30px", width: "600px", borderRadius: "5px" }}
                placeholder="Enter skill(s) reqiured (separated by commas)"
                value={skillsRequired}
                onChange={(e) => setSkillsRequired(e.target.value)}
                type="text"
              />
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Job location(s){" "}
              </label>
              <input
                style={{ height: "30px", width: "600px", borderRadius: "5px" }}
                placeholder="Enter location(s) (separated by commas)"
                value={jobLocation}
                onChange={(e) => setJobLocation(e.target.value)}
                type="text"
              />
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label
                style={{
                  fontSize: "18px",
                  marginTop: "10px",
                  marginBottom: "4px",
                }}
                htmlFor=""
              >
                Job Description
              </label>
              <input
                style={{
                  height: "100px",
                  width: "600px",
                  borderRadius: "5px",
                  textAlign: "top",
                }}
                placeholder="Enter job description"
                value={jobDescription}
                onChange={(e) => setJobDescription(e.target.value)}
                type="text"
              />
            </div>

            <div className="flex flex-col align-center justify-center">
              <button
                style={{
                  backgroundColor: "#fcd34d",
                  borderColor: "#fcd34d",
                  borderRadius: "5px",
                  alignSelf: "center",
                  height: "50px",
                  width: "150px",
                  marginTop: "25px",
                  fontSize: "20px",
                }}
                type="submit"
              >
                Post
              </button>
            </div>
          </form>
          {/* <div
            className="flex flex-col align-center justify-center"
            style={{ marginTop: "10px" }}
          >
          </div> */}
        </div>
      </div>
    </>
  );
};

export default PostJob;
