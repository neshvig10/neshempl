import { React } from "react";

const SearchBar = () => {
  return (
    <>
      <div
        style={{ marginTop: "30px", marginBottom: "100px" }}
        className="flex flex-row justify-around"
      >
        <div>
          <input
            style={{ height: "40px", width: "350px" }}
            placeholder="Search Jobs"
            type="text"
          />
          <input
            style={{ height: "40px", width: "200px" }}
            type="select"
            placeholder="Select Location"
          />
          <button>Search</button>
        </div>
      </div>
    </>
  );
};

export default SearchBar;
