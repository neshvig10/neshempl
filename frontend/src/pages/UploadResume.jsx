import axios from "axios";
import { React, useEffect, useState } from "react";
import { Document } from "react-pdf";
import { useNavigate } from "react-router-dom";
import ResumeViewer from "../components/ResumeViewer";

const UploadResume = () => {
  const [uploadFile, setUploadFile] = useState();
  const [resumeFiles, setResumeFiles] = useState([]);
  const [selectedResumeId, setselectedResumeId] = useState(0);
  const [pdfUrl,setPdfUrl] = useState();
  const navigate = useNavigate();

  const getResume = async () => {
    const selectedResumeFile = await axios.get(
      "http://localhost:8080/api/resume/getresume?resumeId=" + selectedResumeId,
      {
        responseType: "blob", // <- important to get the binary data!
      }
    );
    const file = new Blob([selectedResumeFile.data], { type: 'application/pdf' });
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
        <div
          style={{ width: "400px", marginTop: "50px", marginLeft: "100px" }}
          className="flex flex-col justify-between"
        >
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
                  <option key={resumeFile.resumeId} value={resumeFile.resumeId}>
                    {resumeFile.resumeName}
                  </option>
                ))}
              </select>
              <button
                style={{
                  marginTop: "20px",
                  height: "30px",
                  width: "120px",
                  backgroundColor: "#fcd34d",
                  borderColor: "#fcd34d",
                  borderRadius: "4px",
                }}
              >
                Find Jobs
              </button>
            </div>

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

          <div style={{ borderColor: "#fcd34d" }}>
            <ResumeViewer pdfUrl={pdfUrl}></ResumeViewer>
            hello
            {selectedResumeId}
          </div>
        </div>
        <div
          style={{
            backgroundColor: "#FFE9A2FF",
            width: "100hw",
            height: "100vh",
            marginLeft: "100px",
            marginTop: "30px",
            paddingLeft: "30px",
          }}
        >
          <h3>Job Results</h3>
        </div>
      </div>
    </>
  );
};

export default UploadResume;
