import {BASE_API_URL} from "../../api/baseUrl";
import axios from 'axios'


const BASE_AUTH_URL = BASE_API_URL + '/api/auth';

class AuthenticationService{

    login(user){
        return axios.post(BASE_AUTH_URL + '/signin', user);
    }

    register(user){
        return axios.post(BASE_AUTH_URL + '/register', user);
    }
}

export default new AuthenticationService();
