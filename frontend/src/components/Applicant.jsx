import axios from "axios";
import { React, useEffect, useState } from "react";

const Applicant = (props) => {
  console.log("applicantprops", props.applicant);

  const [pdfUrl, setPdfUrl] = useState();
  const [AIAnalysis, setAIAnalysis] = useState();

  const getAIAnalysis = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/jobapplication/analyseJobApplication" +
        "?resumeId=" +
        props.applicant[1].resumeId +
        "&jobId=" +
        props.jobId
    );
    setAIAnalysis(response.data);
    console.log("AIAnalysis", response.data);
  };

  const getResume = async () => {
    const selectedResumeFile = await axios.get(
      "http://localhost:8080/api/resume/getresume?resumeId=" +
        props.applicant[1].resumeId,
      {
        responseType: "blob", // <- important to get the binary data!
      }
    );
    const file = new Blob([selectedResumeFile.data], {
      type: "application/pdf",
    });
    const fileURL = URL.createObjectURL(file);
    console.log("fileUrl", fileURL);

    setPdfUrl(fileURL);
    console.log(selectedResumeFile);
  };

  return (
    <>
      <div style={{ border: "solid", borderRadius : "5px", borderColor : "#FFC411FF" }}>
        <h3>{props.applicant[0].userName}</h3>

        <div className="flex flex-row justify-between">
          <div className="w-3/5">
            {!pdfUrl ? (
              <button
                style={{ alignSelf: "center" }}
                onClick={() => {
                  getResume();
                }}
              >
                Click to open Resume
              </button>
            ) : (
              <div>
                <button
                  onClick={() => {
                    setPdfUrl(null);
                  }}
                >
                  Hide Resume
                </button>
                <iframe
                  src={pdfUrl}
                  title="Resume Preview"
                  width="100%"
                  height="600px"
                  style={{ border: "none", marginLeft: "20px" }}
                />
              </div>
            )}
          </div>

          <div style={{ border :"solid", borderColor : "#FFCB30FF", backgroundColor : "#FFE492FF", marginLeft: "30px" }} className="w-2/5">
            {AIAnalysis ? (
              <div>
                <button
                  onClick={() => {
                    setAIAnalysis(null);
                  }}
                >
                  Hide AI Analysis
                </button>
                <div>
                  <div style={{margin : "10px"}}>Match Score : {AIAnalysis.match}</div>
                  <div style={{margin : "10px"}}>Reason : {AIAnalysis.reason}</div>
                </div>
              </div>
            ) : (
              <button
                onClick={() => {
                  getAIAnalysis();
                }}
              >
                Click here for AI analysis
              </button>
            )}

            <div>
              <button>Schedule an Interview</button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Applicant;
