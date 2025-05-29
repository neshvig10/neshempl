import { React, useEffect, useState } from "react";
import JobListEmployee from "../components/JobListEmployee";
import JobListEmployer from "../components/JobListEmployer";
import SearchBar from "../components/SearchBar";
import JobDescription from "../components/JobDescription";
import axios from "axios";
import { DescriptionContext } from "../contexts/DescriptionContext";

const HomePage = () => {
  const [jobs, setJobs] = useState([]);
  // const [jobIndex, setJobIndex] = useState(0);
  const [jobsPostedByUser, setJobsPostedByUser] = useState([]);

  const fetchJobsPostedByUser = async () => {
    if (localStorage.getItem("userRoleAuth") === "EMPLOYER") {
      const response = await axios.get(
        "http://localhost:8080/api/job/jobpostedbyuser?userId=" +
          localStorage.getItem("userId")
      );
      console.log(response);
      console.log("jobsfetchedbyhomepage",response.data);
      const jobsList = [];
      for (let i = 0;i<response.data.length;i++){
        const response1 = await axios.get("http://localhost:8080/api/job/jobbyid?jobId="+response.data[i]);
        jobsList.push(response1.data);
      }
      setJobsPostedByUser(jobsList);
    }
  };

  const retrieveJobs = async () => {
    const jobList = await axios.get("http://localhost:8080/api/job/getjobs");
    console.log("jobsfetchedbyhomepage",jobList.data);
    // console.log(jobList.data[2]);
    setJobs(jobList.data);
  };

  useEffect(() => {
    retrieveJobs();
    fetchJobsPostedByUser();
    
  }, []);

  return (
    <>
      {localStorage.getItem("userRoleAuth") === "EMPLOYER" ? (
        <div className="flex flex-col" style={{ width : "650px", paddingTop: "70px" }}>
          <h2>Job(s) posted by you : </h2>
          <div className="flex flex-row justify-around">
            <div
              className="flex flex-row justify-around"
              style={{
                backgroundColor: "#FFEAACFF",
                width: "100%",
              }}
            >
              <JobListEmployer jobs={jobsPostedByUser}></JobListEmployer>
            </div>
          </div>
        </div>
      ) : (
        <div className="flex flex-col" style={{ paddingTop: "70px" }}>
          <div>
            <SearchBar></SearchBar>
          </div>
          <div className="flex flex-row justify-around">
            <div
              className="flex flex-row justify-around"
              style={{
                backgroundColor: "#FFEAACFF",
                width: "100%",
              }}
            >
              <JobListEmployee
                jobs={jobs}
              ></JobListEmployee>
            </div>
            <div
              style={{
                backgroundColor: "#FFD76BFF",
                height: "100%",
                width: "100%",
                border: "2px solid",
              }}
            >
              <JobDescription job={jobs}></JobDescription>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default HomePage;
