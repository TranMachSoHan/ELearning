export const API_BASE_URL = "http://ec2-3-110-89-38.ap-south-1.compute.amazonaws.com:8081";
export const ACCESS_TOKEN = "token";

export const OAUTH2_REDIRECT_URI = "http://redemy-app-deploy.s3-website.ap-south-1.amazonaws.com/:3000/oauth2/redirect";

export const GOOGLE_AUTH_URL =
  API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;
