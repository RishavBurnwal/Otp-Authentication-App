import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./Pages/Login";
import VerifyOtp from "./Pages/VerifyOtp";
import Welcome from "./Pages/Welcome";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/verify" element={<VerifyOtp />} />
        <Route path="/welcome" element={<Welcome />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
