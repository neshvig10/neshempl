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

  const findJobsForResume = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/resume/analyzeresume?resumeId=" +
        selectedResumeId
    );
    console.log(response.data.data);
    setJobsFound(response.data.data);

    //setJobsFound(response.data);
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
          <div style={{ paddingLeft: "100px", paddingBottom: "20px" }}>
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
          <h3>Job Results</h3>
          {/* {resumeFiles.map((resumeFile) => (
            <option key={resumeFile.resumeId} value={resumeFile.resumeId}>
              {resumeFile.resumeName}
            </option>
          ))} */}
          {jobsFound &&
            jobsFound.map((jobFound) => (
              <div
                style={{
                  border: "solid",
                  borderColor: "#9B7700FF",
                  backgroundColor: "#FFF0BEFF",
                  padding: "5px",
                }}
                key={jobFound.id}
              >
                <div className="flex flex-row justify-between">
                  <div><a
                  style={{ textDecoration: "none", fontColor: "#3B2E00FF" }}
                  target="_blank"
                  href={jobFound.url}
                >
                  <h3>{jobFound.title}</h3>
                </a>
                <a
                  style={{ textDecoration: "none", fontColor: "#3B2E00FF" }}
                  target="_blank"
                  href={jobFound.company.url}
                >
                  <p>Company : {jobFound.company.name}</p>
                </a>
                <p>Location : {jobFound.location}</p></div>
                  <div>
                    <img style={{marginTop : "20px",height : "50px"}} src={jobFound.company.logo} alt="" />
                  </div>
                </div>
                
              </div>
            ))}
        </div>
      </div>
    </>
  );
};

export default UploadResume;
