import { API_BASE_URL } from "../constants";

const request = (options) => {
    const headers = new Headers({
        "Content-Type": "application/json",
    });

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

export function getStudents() {
    return request({
        url: API_BASE_URL + "/profile/all-students-string",
        method: "GET",
    });
}

export function getProfessors() {
    return request({
        url: API_BASE_URL + "/profile/all-professors-string",
        method: "GET",
    });
}