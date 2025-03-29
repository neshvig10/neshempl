import { React, useEffect, useState } from "react";
import JobList from "../components/JobList";
import SearchBar from "../components/SearchBar";
import JobDescription from "../components/JobDescription";
import axios from "axios";

const HomePage = () => {
  const [jobs, setJobs] = useState([]);
  const [jobIndex, setJobIndex] = useState(1);

  const retrieveJobs = async () => {
    const jobList = await axios.get("http://localhost:8080/api/job/getjobs");
    console.log(jobList.data);
    console.log(jobList.data[2]);

    setJobs(jobList.data);
  };

  useEffect(() => {
    retrieveJobs();
  }, []);

  return (
    <>
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
            <JobList
              jobs={jobs}
              jobIndex={jobIndex}
              setJobIndex={setJobIndex}
            ></JobList>
          </div>
          <div
            style={{
              backgroundColor: "#FFD76BFF",
              height: "100%",
              width: "100%",
              border : "2px solid",
            }}
          >
            <JobDescription job={jobs} jobIndex={jobIndex}></JobDescription>
          </div>
        </div>
      </div>
    </>
  );
};

export default HomePage;
