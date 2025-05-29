import axios from "axios";
import { React, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const UploadResume = () => {
  const [uploadFile, setUploadFile] = useState();
  const [resumeFiles, setResumeFiles] = useState([]);
  const [selectedResumeId, setselectedResumeId] = useState(0);
  const [pdfUrl, setPdfUrl] = useState();
  const [jobsFound, setJobsFound] = useState([]);
  const navigate = useNavigate();

  const applyToJob = async (jobId) => {
    const response = await axios.post(
      "http://localhost:8080/api/job/applytojob?" +
        "jobId=" +
        jobId +
        "&userId=" +
        localStorage.getItem("userId") +
        "&resumeId=" +
        selectedResumeId
    );
    console.log(response.data);
  };

  const appliedToJobOrNot = async (jobId) => {
    const response = await axios.get(
      "http://localhost:8080/api/job/appliedtojobornot?" +
        "jobId=" +
        jobId +
        "&userId=" +
        localStorage.getItem("userId")
    );
    console.log(response.data);
    return response.data;
  };

  const findJobsForResume = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/resume/analyzeresume?resumeId=" +
        selectedResumeId
    );
    console.log(response.data);
    setJobsFound(response.data);
  };

  const deleteResume = async () => {
    const response = await axios.delete(
      "http://localhost:8080/api/resume/deleteresume?resumeId=" +
        selectedResumeId
    );
    console.log(response.data);
    window.location.reload();
  };

  const getResume = async () => {
    const selectedResumeFile = await axios.get(
      "http://localhost:8080/api/resume/getresume?resumeId=" + selectedResumeId,
      {
        responseType: "blob", // <- important to get the binary data!
      }
    );
    const file = new Blob([selectedResumeFile.data], {
      type: "application/pdf",
    });
    const fileURL = URL.createObjectURL(file);
    console.log("fileUrl",fileURL);
    
    setPdfUrl(fileURL);
    console.log(selectedResumeFile);
  };

  const uploadResume = async (e) => {
    e.preventDefault();

    console.log("resumefilelog", uploadFile);

    let fileData = new FormData();
    fileData.append("file", uploadFile);

    const resumeResponse = await axios.post(
      "http://localhost:8080/api/resume/uploadresume?userId=" +
        localStorage.getItem("userId"),
      fileData,
      {
        headers: {
          "Content-type": "multipart/form-data",
        },
      }
    );

    console.log(resumeResponse.data);
    navigate("/uploadresume");
    window.location.reload();
  };

  const fetchPDFs = async () => {
    const resumeFiles = await axios.get(
      "http://localhost:8080/api/resume/getresumesforuser?" +
        "userId=" +
        localStorage.getItem("userId")
    );
    console.log("resumefiles", resumeFiles.data);
    setResumeFiles(resumeFiles.data);
    setselectedResumeId(resumeFiles.data[0].resumeId);
  };

  useEffect(() => {
    fetchPDFs();
  }, []);

  return (
    <>
      <div
        className="flex flex-col justify-around"
        style={{
          alignSelf: "center",
          marginLeft: "600px",
          paddingTop: "100px",
          width: "200px",
        }}
      >
        <form
          action=""
          onSubmit={uploadResume}
          type="multipart/form-data"
          method="post"
        >
          <label htmlFor="">Upload your Resume</label>
          <input
            style={{
              marginTop: "10px",
              height: "30px",
              borderColor: "#fcd34d",
              borderRadius: "4px",
            }}
            id="resumefile"
            // value={uploadFile}
            onChange={(e) => setUploadFile(e.target.files[0])}
            accept="application/pdf"
            type="file"
          />
          <button
            style={{
              marginTop: "10px",
              height: "30px",
              width: "120px",
              backgroundColor: "#fcd34d",
              borderColor: "#fcd34d",
              borderRadius: "4px",
            }}
            type="submit"
          >
            Upload
          </button>
        </form>
      </div>

      <div className="flex flex-row">
        <div style={{ marginLeft: "150px" }} className="flex flex-col">
          <div>
            <div className="flex flex-row">
              <div className="flex flex-col">
                <label
                  style={{ fontSize: "20px", marginBottom: "10px" }}
                  htmlFor=""
                >
                  Choose Resume for job search :
                </label>
                <select
                  name=""
                  id=""
                  onChange={(e) => {
                    setselectedResumeId(e.target.value);
                  }}
                >
                  {resumeFiles.map((resumeFile) => (
                    <option
                      key={resumeFile.resumeId}
                      value={resumeFile.resumeId}
                    >
                      {resumeFile.resumeName}
                    </option>
                  ))}
                </select>
              </div>
              <div>
                <button
                  onClick={getResume}
                  style={{
                    marginTop: "20px",
                    height: "30px",
                    width: "120px",
                    backgroundColor: "#fcd34d",
                    borderColor: "#fcd34d",
                    borderRadius: "4px",
                  }}
                >
                  Display Resume
                </button>
              </div>
            </div>
          </div>
          <div>
            <iframe
              src={pdfUrl}
              title="Resume Preview"
              width="100%"
              height="600px"
              style={{ border: "none" }}
            />
          </div>
          <div
            className="flex flex-row"
            style={{ paddingLeft: "100px", paddingBottom: "20px" }}
          >
            <button
              style={{
                marginTop: "20px",
                height: "30px",
                width: "120px",
                backgroundColor: "#fcd34d",
                borderColor: "#fcd34d",
                borderRadius: "4px",
              }}
              onClick={deleteResume}
            >
              Delete Resume
            </button>
            <button
              style={{
                marginTop: "20px",
                height: "30px",
                width: "120px",
                backgroundColor: "#fcd34d",
                borderColor: "#fcd34d",
                borderRadius: "4px",
              }}
              onClick={findJobsForResume}
            >
              Find Jobs
            </button>
          </div>
        </div>

        <div
          style={{
            backgroundColor: "#FFE9A2FF",
            height: "650px",
            width: "800px",
            marginLeft: "150px",
            marginTop: "30px",
            paddingLeft: "30px",
            overflow: "auto",
          }}
        >
          <div></div>
          <h3>Job Results</h3>
          {jobsFound ? (
            jobsFound.map((job) => (
              <div
                key={job.jobId[0]}
                style={{
                  backgroundColor: "#FFE89DFF",
                  borderStyle: "solid",
                  margin: "4px",
                  padding: "4px",
                  borderWidth: "2px",
                }}
              >
                <h3>{job.jobTitle[0]}</h3>
                <h4>Description : {job.jobDescription[0]}</h4>
                <p>
                  Experience Required : {job.jobExperienceRequired[0]} years
                </p>
                <p>Matching Score : {job.matchScore}</p>
                <p>Match Reason : {job.reasonForScore}</p>

                {!appliedToJobOrNot(job.jobId[0]) ? (
                  <button
                    onClick={() => {
                      applyToJob(job.jobId[0]);
                    }}
                    style={{
                      marginLeft: "660px",
                      marginTop: "20px",
                      height: "30px",
                      width: "120px",
                      backgroundColor: "#fcd34d",
                      borderColor: "#fcd34d",
                      borderRadius: "4px",
                    }}
                  >
                    Apply Now
                  </button>
                ) : (
                  <div
                    style={{
                      marginLeft: "660px",
                      marginTop: "20px",
                      height: "30px",
                      width: "120px",
                      backgroundColor: "#FC7F4DFF",
                      borderColor: "#fcd34d",
                      borderRadius: "4px",
                    }}
                  >
                    Applied already
                  </div>
                )}
              </div>
            ))
          ) : (
            <div>Please try again</div>
          )}
        </div>
      </div>
    </>
  );
};

export default UploadResume;
