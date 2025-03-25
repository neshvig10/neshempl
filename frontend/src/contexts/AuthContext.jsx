import {createContext, React, useState} from 'react';

export const AuthContext = createContext();



export const AuthProvider = ({children})=> {

    const [userId,setUserId] = useState();
    const [isLoggedIn,setIsLoggedIn] = useState(false);
    const [userRoleAuth,setUserRoleAuth] = useState("EMPLOYEE");


    const login =(jwtToken)=>{
        localStorage.setItem("jwtToken",jwtToken);
    }

    const logout =()=>{
        localStorage.setItem("jwtToken",null);
    }

    

    return (
        <AuthContext.Provider value={{userId,isLoggedIn,userRoleAuth,setUserRoleAuth,setUserId,setIsLoggedIn,login,logout}}>{children}</AuthContext.Provider>
    );

}





