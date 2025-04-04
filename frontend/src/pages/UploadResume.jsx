import axios from "axios";
import { React, useEffect, useState } from "react";

const UploadResume = () => {
  const [uploadFile, setUploadFile] = useState();
  const [resumeFiles, setResumeFiles] = useState([]);

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
  };

  const fetchPDFs = async () => {
    const resumeFiles = await axios.get(
      "http://localhost:8080/api/resume/getresumesforuser?" +
        "userId=" +
        localStorage.getItem("userId")
    );
    console.log("resumefiles", resumeFiles.data);
    setResumeFiles(resumeFiles.data);
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
          className="flex flex-row"
        >
          <div className="flex flex-col">
            <label htmlFor="">Choose Resume for job search :</label>
            <select name="" id="">
              {resumeFiles.map((resumeFile) => {
                <option value={resumeFile.name}></option>;
              })}
            </select>
          </div>

          <button
            style={{
              marginTop: "10px",
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
        <div
          style={{
            backgroundColor: "#FFE9A2FF",
            width: "100hw",
            height : "100vh",
            marginLeft: "100px",
            marginTop : "30px",
            paddingLeft : "30px"
          }}
        >
          <h3>Job Results</h3>
        </div>
      </div>
    </>
  );
};

export default UploadResume;
