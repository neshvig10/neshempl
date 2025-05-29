import React,{ createContext, useState } from "react";

export const DescriptionContext = createContext();

export const DescriptionProvider = ({ children }) => {

  const [jobIndex, setJobIndex] = useState(0);
  console.log("descriptioncontext",jobIndex);
  


  return (
    <DescriptionContext.Provider
      value={{ jobIndex, setJobIndex }}
    >
      {children}
    </DescriptionContext.Provider>
  );
};
