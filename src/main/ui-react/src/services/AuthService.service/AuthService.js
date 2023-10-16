import {BASE_API_URL} from "../../api/baseUrl";
import axios from 'axios'

const BASE_AUTH_URL = BASE_API_URL + '/api/auth'

class AuthService{

    login(user){
        return axios.post(BASE_AUTH_URL + '/signin');
    }

    register(user){
        return axios.post(BASE_API_URL + '/register');
    }
}

export default AuthService;
