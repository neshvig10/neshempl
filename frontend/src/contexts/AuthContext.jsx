import {createContext, React, useEffect, useState} from 'react';

export const AuthContext = createContext(globalThis);



export const AuthProvider = ({children})=> {

    useEffect(()=> {
        localStorage.setItem("userRoleAuth","EMPLOYEE")
    },[])


    const login =(jwtToken)=>{
        localStorage.setItem("isLoggedIn",true);
        localStorage.setItem("jwtToken",jwtToken);
    }

    const logout =()=>{
        localStorage.setItem("jwtToken",null);
        localStorage.setItem("isLoggedIn",false);
    }

    

    return (
        <AuthContext.Provider value={{login,logout}}>{children}</AuthContext.Provider>
    );

}





