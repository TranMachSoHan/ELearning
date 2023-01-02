import { ACCESS_TOKEN } from "../../constants";
import { Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

const OAuth2RedirectHandler = () => {
  const navigate = useNavigate();
  const getUrlParameter = (name) => {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");

    var results = regex.exec(window.location.search);
    return results === null
      ? ""
      : decodeURIComponent(results[1].replace(/\+/g, " "));
  };

  const token = getUrlParameter("token");
  console.log(token);

  const error = getUrlParameter("error");

  useEffect(() => {
    if (token) {
      localStorage.setItem(ACCESS_TOKEN, token);
      navigate("/");
    } else {
      navigate("/login");
    }
  });
};

export default OAuth2RedirectHandler;
