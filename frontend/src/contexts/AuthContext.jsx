import {createContext, React} from 'react';

export const AuthContext = createContext(globalThis);



export const AuthProvider = ({children})=> {

    const login =(jwtToken)=>{
        localStorage.setItem("isLoggedIn",true);
        localStorage.setItem("jwtToken",jwtToken);
    }

    const logout =()=>{
        localStorage.removeItem("isLoggedIn");
        localStorage.removeItem("userRoleAuth");
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("userId");
    }



    return (
        <AuthContext.Provider value={{login,logout}}>{children}</AuthContext.Provider>
    );

}





