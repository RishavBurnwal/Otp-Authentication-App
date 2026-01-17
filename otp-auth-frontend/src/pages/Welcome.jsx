import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/auth.css";

function Welcome() {
  const [user, setUser] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/auth/me", {
      headers: { Authorization: "Bearer " + token }
    })
      .then(res => {
        if (!res.ok) navigate("/");
        return res.json();
      })
      .then(data => setUser(data.identifier));
  }, []);

  return <h2>Welcome{user}</h2>;
}

export default Welcome;
