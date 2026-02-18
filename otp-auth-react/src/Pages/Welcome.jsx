import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../Styles/auth.css";

function Welcome() {
  const [user, setUser] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/");
      return;
    }

    fetch("http://localhost:8080/auth/me", {
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then((res) => {
        if (!res.ok) {
          navigate("/");
          return;
        }
        return res.json();
      })
      .then((data) => {
        if (data) setUser(data.identifier);
      });
  }, [navigate]);

  return (
    <div className="container">
      <div className="card">
        <h2>Welcome {user}</h2>
      </div>
    </div>
  );
}

export default Welcome;
