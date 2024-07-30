import { Routes, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage.tsx";
import LoginPage from "./pages/LoginPage.tsx";
import SignUpPage from "./pages/SignUpPage.tsx";
import ForgotPasswordPage from "./pages/ForgotPasswordPage.tsx";
import HomePage from "./pages/HomePage.tsx";

function App() {
  return (
    <>
      <Routes>
          <Route path="/" element={ <LandingPage/> } />
          <Route path="/login" element={ <LoginPage/> } />
          <Route path="/forgotpassword" element={ <ForgotPasswordPage/> } />
          <Route path="/signup" element={ <SignUpPage/> } />
          <Route path="/home" element={ <HomePage/> } />
      </Routes>
    </>
  )
}

export default App
