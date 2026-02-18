import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../Styles/auth.css";

function VerifyOtp() {
  const [otp, setOtp] = useState("");
  const navigate = useNavigate();
  const identifier = localStorage.getItem("identifier");

  const verifyOtp = async () => {
    const res = await fetch("http://localhost:8080/auth/verify-otp", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ identifier, otp })
    });

    const data = await res.json();

    if (!res.ok) {
      alert(data.error);
      return;
    }

    localStorage.setItem("token", data.token);
    navigate("/welcome");
  };

  return (
    <div className="container">
      <div className="card">
        <h2>Verify OTP</h2>
        <input
          type="text"
          placeholder="Enter OTP"
          value={otp}
          onChange={(e) => setOtp(e.target.value)}
        />
        <button onClick={verifyOtp}>Verify</button>
      </div>
    </div>
  );
}

export default VerifyOtp;
