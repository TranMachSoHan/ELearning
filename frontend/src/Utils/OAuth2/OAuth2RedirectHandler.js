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

  function paramsToObject(entries) {
    const result = {};
    for (const [key, value] of entries) {
      // each 'entry' is a [key, value] tupple
      result[key] = value;
    }
    return result;
  }
  const a = getUrlParameter("token");
  console.log(a);
  const res = "?" + a.slice(12, a.length - 1);
  console.log(res);
  const foo = res.replace(/\, /gi, "&");
  console.log(foo);
  const token = Object.fromEntries(new URLSearchParams(foo));

  console.log(token);

  const error = getUrlParameter("error");

  useEffect(() => {
    if (token) {
      localStorage.setItem(ACCESS_TOKEN, JSON.stringify(token));
      navigate("/");
    } else {
      navigate("/login");
    }
  });
};

export default OAuth2RedirectHandler;
