import axios from "axios"
import { BASE_API_URL } from "../../api/baseUrl";
import { authHeaders } from "../../common/models/AuthHeaders";

const BASE_EXPENSE_URL = BASE_API_URL + '/api/expense/'

class Expense{

    addExpense(expense){
        return axios.post(BASE_EXPENSE_URL+'addExpense', expense );
    }

    getWeeklyExpenses(){
        return axios.get(BASE_EXPENSE_URL+'groupByWeek');
    }

    getMonthlyExpenses(){
        return axios.get(BASE_EXPENSE_URL+'groupByMonth');
    }

    getLineChartData(){
        return axios.get(BASE_EXPENSE_URL+'getCategoryCount');
    }

    getPurchaseCountForCurrentWeek(){
        return axios.get(BASE_EXPENSE_URL+'getPurchaseCountForCurrentWeek');
    }
}

export default new Expense();
