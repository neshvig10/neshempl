import { React, useContext, useEffect, useState } from "react";
import { AuthContext } from "../contexts/AuthContext";
import axios from "axios";

const Navbar = () => {
  const { logout } = useContext(AuthContext);

  const [isLoggedIn, setIsLoggedIn] = useState();
  const [userRoleAuth, setuserRoleAuth] = useState();
  const [userId, setUserId] = useState();

  const getUserId = async () => {
    const userid = await axios.get(
      "http://localhost:8080/api/user/useridfromjwt" +
        "?jwt=" +
        localStorage.getItem("jwtToken")
    );
    console.log(userid);
    setUserId(userid.data);
  };

  useEffect(() => {
    setIsLoggedIn(localStorage.getItem("isLoggedIn"));
    setuserRoleAuth(localStorage.getItem("userRoleAuth"));
    setUserId(getUserId());
  }, []);

  return (
    <>
      <div
        className="flex flex-row justify-between w-full fixed top-0 bg-amber-300"
        style={{
          backgroundColor: "#fcd34d",
          height: "35px",
          paddingTop: "20px",
          paddingLeft: "5px",
          paddingRight: "5px",
        }}
      >
        <div className="flex flex-row">
          <div>
            <a
              style={{ padding: "3px" }}
              className="m-3 text-amber-600 no-underline"
              href="/"
            >
              neshempl
            </a>
          </div>
          <div>
            {isLoggedIn ? (
              <div>
                {userRoleAuth === "EMPLOYEE" ? (
                  <a
                    className="m-3 text-amber-600 no-underline"
                    href="/uploadresume"
                  >
                    Upload Resume
                  </a>
                ) : (
                  <a
                    className="m-3 text-amber-600 no-underline"
                    href="/postjob"
                  >
                    PostJob
                  </a>
                )}
              </div>
            ) : (
              <div>
                <a
                  style={{ padding: "3px" }}
                  className="m-3 text-amber-600 no-underline"
                  href="/uploadresume"
                >
                  Upload Resume
                </a>
                <a
                  style={{ padding: "3px" }}
                  className="m-3 text-amber-600 no-underline"
                  href="/postjob"
                >
                  Post Job
                </a>
              </div>
            )}
          </div>
        </div>

        <div className="flex flex-row">
          {!isLoggedIn ? (
            <div>
              <a
                style={{ padding: "3px" }}
                className="m-3 text-amber-600 no-underline"
                href="/login"
              >
                Login
              </a>
              <a
                style={{ padding: "3px" }}
                className="m-3 text-amber-600 no-underline"
                href="/register"
              >
                Register
              </a>
            </div>
          ) : (
            <div>
              <a
                style={{ padding: "3px" }}
                onClick={logout}
                className="m-3 text-amber-600 no-underline"
                href="/"
              >
                Logout
              </a>
              <a
                style={{ padding: "3px" }}
                className="m-3 text-amber-600 no-underline"
                href={`/profile/${userId}`}
              >
                Profile
              </a>
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default Navbar;
