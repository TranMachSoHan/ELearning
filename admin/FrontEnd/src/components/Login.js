import React, { useState } from "react";
import PropTypes from "prop-types";

export function Login({ setToken }) {
  const [userName, setUserName] = useState();
  const [password, setPassword] = useState();
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (userName === "admin" && password === "adminpass") {
      setToken("token");
    } else {
      alert("Wrong password or Username");
    }
  };
  return (
    <div class="flex flex-col mt-5 align-center p-4">
      <div class="flex flex-col p-8 bg-white rounded shadow-sm">
        <div class="align-center md:w-1/2 px-3 mb-6 md:mb-0">
          <h1 class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
            Please Log in
          </h1>
          <form onSubmit={handleSubmit}>
            <label>
              <label
                class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                for="user-name"
              >
                Name
              </label>
              <input
                class="appearance-none block w-full bg-primary-50 rounded py-3 px-4 mb-3 leading-tight focus:outline-none"
                id="use-name"
                type="text"
                onChange={(e) => setUserName(e.target.value)}
              />
            </label>
            <label>
              <label
                class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                for="password"
              >
                Name
              </label>
              <input
                class="appearance-none block w-full bg-primary-50 rounded py-3 px-4 mb-3 leading-tight focus:outline-none"
                id="grid-first-name"
                type="password"
                onChange={(e) => setPassword(e.target.value)}
              />
            </label>
            <div>
              <button
                class="appearance-none block bg-primary-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none"
                id="grid-first-name"
                type="submit"
              >
                Submit
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired,
};
