import { React, useState } from "react";

const PostJob = () => {
    
    const [message,setMessage] = useState();
    const [jobTitle,setJobTitle] = useState();
    const [companyName,setCompanyName] = useState();
    const [skillsRequired,setSkillsRequired] = useState([]);



    const messageDisplay = (message)=>{
        setMessage(message);
        document.getElementById("message").style.color = "red";
    }

    const postJob = ()=> {


    }

  return (
    <>
      <div className="flex flex-column align-center justify-center h-screen">
        <div
          className="border-2"
          style={{
            borderColor: "#fcd34d",
            height: "270px",
            width: "320px",
            paddingRight: "20px",
            paddingLeft: "20px",
            paddingBottom: "20px",
            marginTop: "350px",
          }}
        >
          <div>
            <h3>Job Posting</h3>
          </div>
          <form action="" onSubmit={postJob} method="post">
            <div id="message">
              <p>{message}</p>
            </div>
            <div
              className="flex flex-col justify-between"
              style={{ marginTop: "5px", marginBottom: "5px" }}
            >
              <label htmlFor="">Job Title</label>
              <input
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
              <label htmlFor="">Company Name</label>
              <input
                placeholder="Enter Company Name"
                value={companyName}
                onChange={(e) => setCompanyName(e.target.value)}
                type="number"
              />
            </div>

            <div className="flex flex-col align-center justify-center">
              <button
                style={{
                  backgroundColor: "#fcd34d",
                  borderColor: "#fcd34d",
                  borderRadius: "5px",
                  height: "30px",
                  width: "100px",
                  marginTop: "5px",
                }}
                type="submit"
              >
                Post
              </button>
            </div>
          </form>
          <div
            className="flex flex-col align-center justify-center"
            style={{ marginTop: "10px" }}
          >
          </div>
        </div>
      </div>
    </>
  );
};

export default PostJob;
