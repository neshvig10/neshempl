import { createContext, React, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    

    // Load user from localStorage when the app starts
    useEffect(() => {
        const savedToken = localStorage.getItem("jwtToken");
        if (savedToken) {
            setUser(localStorage.getItem("userPhone"));  // Set the user if token exists
        }
    }, []);

    const login = (user) => {
        setUser(user);
        localStorage.setItem("userPhone", user);  // Save the user token to localStorage
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem("jwtToken"); 
        // Optionally reload the page or redirect to login page
    };

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};
