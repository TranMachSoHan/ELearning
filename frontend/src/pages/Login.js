import SectionTitle from "../components/SectionTitle";
import Button from "../components/Button";
import { useRef, useState, useEffect, useContext } from "react";
// import { setProfileSession } from "../utils/Common";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import AuthContext from "../context/AuthProvider";

const Login = () => {
  const OAUTH2_REDIRECT_URI = "http://localhost:3000/oauth2/redirect";

  const API_BASE_URL = "http://localhost:8080";
  const GOOGLE_AUTH_URL =
    API_BASE_URL +
    "/oauth2/authorize/google?redirect_uri=" +
    OAUTH2_REDIRECT_URI;

  const navigate = useNavigate();
  const { setAuth } = useContext(AuthContext);
  const userRef = useRef();
  const errorRef = useRef();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    setError("");
  }, [email, password]);

  const login = (e) => {
    e.preventDefault(e);
    setError(null);
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Cookie", "JSESSIONID=848F70A441B5F7E026D32CFD485E4A5C");

    var raw = JSON.stringify({
      email: email,
      password: password,
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch("http://localhost:8080/auth/sign-in", requestOptions)
      .then((response) => {
        if (response.status === 401) {
          setError("Invalid email or password");
          setPassword("");
          throw new Error(response.status);
        } else if (response.ok) {
          return response.json();
        }
      })
      .then((result) => {
        localStorage.setItem("user", JSON.stringify(result));
        console.log(result);
      })
      .catch((error) => console.log("error", error));
  };

  return (
    <section className="min-h-[700px] pt-24">
      <SectionTitle title="Login"></SectionTitle>
      <div>
        <a href={GOOGLE_AUTH_URL}>Google login</a>
      </div>
      <form onSubmit={login} className="w-1/4 pt-20 mx-auto space-y-6">
        <div className="space-y-2">
          <label htmlFor="email">Email</label>
          <input
            type="text"
            name="email"
            id="email"
            className="block w-full p-2 border border-black"
            placeholder="email@gmail.com"
            required
            ref={userRef}
            onChange={(e) => setEmail(e.target.value)}
            value={email}
          />
        </div>
        <div className="space-y-2">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            name="password"
            id="password"
            className="block w-full p-2 border border-black"
            ref={userRef}
            onChange={(e) => setPassword(e.target.value)}
            value={password}
            required
          />
        </div>
        <p
          ref={errorRef}
          className={error ? "text-red-500 text-sm" : "absolute left-72"}
          aria-live="assertive"
        >
          {error}
        </p>

        <Button type="submit" text={"Login"} isPrimary={true}></Button>
      </form>
    </section>
  );
};

export default Login;
