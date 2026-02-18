import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../Styles/auth.css";

function Login() {
  const [identifier, setIdentifier] = useState("");
  const navigate = useNavigate();

  const sendOtp = async () => {
    const res = await fetch("http://localhost:8080/auth/request-otp", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ identifier })
    });

    if (!res.ok) {
      alert("Failed to send OTP");
      return;
    }

    localStorage.setItem("identifier", identifier);
    navigate("/verify");
  };

  return (
    <div className="container">
      <div className="card">
        <h2>Login</h2>
        <input
          type="text"
          placeholder="Email or Phone"
          value={identifier}
          onChange={(e) => setIdentifier(e.target.value)}
        />
        <button onClick={sendOtp}>Send OTP</button>
      </div>
    </div>
  );
}

export default Login;
