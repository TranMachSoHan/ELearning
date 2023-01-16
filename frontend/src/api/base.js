import axios from 'axios';

export default axios.create({
  baseURL: `http://ec2-3-110-89-38.ap-south-1.compute.amazonaws.com:8082`
});