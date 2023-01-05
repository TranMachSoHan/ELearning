import { API_BASE_URL, ACCESS_TOKEN } from "../constants";

const request = (options) => {
  const headers = new Headers({
    "Content-Type": "application/json",
  });

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.append(
      "Authorization",
      "Bearer " + localStorage.getItem(ACCESS_TOKEN)
    );
  }

  const defaults = { headers: headers };
  options = Object.assign({}, defaults, options);

  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export function getCurrentUser() {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return Promise.reject("No access token set.");
  }

  return request({
    url: API_BASE_URL + "/profile/me",
    method: "GET",
  });
}

export function login(loginRequest) {
  return request({
    url: API_BASE_URL + "/auth/sign-in",
    method: "POST",
    body: JSON.stringify(loginRequest),
  });
}

export function signup(signupRequest, type) {
  if (type === "student") {
    return request({
      url: API_BASE_URL + "/auth/sign-up/student",
      method: "POST",
      body: JSON.stringify(signupRequest),
    });
  }
  return request({
    url: API_BASE_URL + "/auth/sign-up/professor",
    method: "POST",
    body: JSON.stringify(signupRequest),
  });
}
