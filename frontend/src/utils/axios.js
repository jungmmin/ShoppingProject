import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: import.meta.env.PROD ?
    '' : 'http://localhost:8080'
})

axiosInstance.interceptors.request.use(function (config) {
  config.headers.Authorization = 'Bearer ' + localStorage.getItem('accessToken');
  return config;
}, function (error) {
  return Promise.reject(error);
})

// jwt 만료시 리프레시
axiosInstance.interceptors.response.use(function (response) {
  return response;
}, function (error) {
  if(error.response.data === 'jwt is expired'){
    window.location.reload();
  }
  return Promise.reject(error);
})

export default axiosInstance;
