import axios from "axios"
import { BASE_API_URL } from "../../api/baseUrl";
import { authHeaders } from "../../common/models/AuthHeaders";

const BASE_AUTH_URL = BASE_API_URL + '/api/expense'

class Expense{



    addExpense(expense){
        return axios.post(BASE_API_URL + '/addExpense', expense, {headers: authHeaders()});
    }
}

export default Expense;