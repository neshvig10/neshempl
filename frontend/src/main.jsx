import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "tailwindcss";
import "./index.css";
import { AuthProvider } from "./contexts/AuthContext.jsx";
import { DescriptionProvider } from "./contexts/DescriptionContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <DescriptionProvider>
      <AuthProvider>
        <App />
      </AuthProvider>
    </DescriptionProvider>
  </StrictMode>
);
