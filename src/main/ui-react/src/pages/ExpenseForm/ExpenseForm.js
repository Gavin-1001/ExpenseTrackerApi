import { useState } from "react";
import Expense from "../../common/models/Expense";
import ExpenseService from './../../services/ExpenseService.service/ExpenseService';
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {setCurrentUser} from "../../redux/store/actions/users";
import Sidebar from "../../components/Navigation/Sidebar/Sidebar";

const ExpenseForm = () => {

    const [errorMessage, setErrorMessage] = useState("");
    const [submitted, setSubmitted] = useState(false);
    const [expense, setExpense] = useState(new Expense("", "", "", "", ""))
    const [loading, setLoading] = useState(false)
    const currentUser = useSelector((state) => state.user);

    const navigate = useNavigate();
    const dispatch = useDispatch();


    const handleChange = (e) => {
        const { name, value } = e.target;
        //console.log(e.target.value)
        setExpense((previousState) => {
            return {
                ...previousState,
                [name]: value,
            };
        });
    };


    const handleExpense = (e) =>{ 
        
        e.preventDefault();
        //stops the login creds being displayed in url

        setSubmitted(true);
        if (!expense.expenseTitle || !expense.expenseDescription || !expense.expensePrice || !expense.expenseDate) {
            return; //checks if username and password fields are not empty
        }
        setLoading(true);


        ExpenseService.addExpense(expense)
            .then((response) => {
            console.log(response.data)

                navigate("/");
            })
            .catch((error) => {
                console.log(error);
                setErrorMessage("USERNAME OR PASSWORD IS NOT VALID!!!");
                setLoading(false);
            });}

    return (
            <div className={"container mt-5"} >
                  <div className="card ms-auto me-auto p-3 shadow-lg custom-card">
                    <h3>Expense Form</h3> {/*NoT really happy with this but style later if you get a chance*/}
                    {errorMessage && (
                        <div className="alert alert-danger">{errorMessage}</div>
                    )}
                      <Sidebar />
                    <form
                        onSubmit={(e) => handleExpense(e)}
                        className={submitted ? "was-validated" : ""}
                        noValidate // does not validate the form
                    >
                        <div className="form-group">
                            {/*USERNAME*/}
                            <label htmlFor="expenseTitle">Expense Title</label>
                            <input
                                type="text"
                                name="expenseTitle"
                                className="form-control"
                                autoComplete="off"
                                placeholder="Enter a title to this expense"
                                value={expense.expenseTitle}
                                onChange={(e) => handleChange(e)}
                                required
                            />
                            {/*DISPLAYS ANY ERROR MESSAGE RELATING TO FIELD*/}
                            <div className="invalid-feedback">Expense Title is required</div>
                        </div>
    
                        <div className="form-group">
                            {/*PASSWORD*/}
                            <label htmlFor="expenseDescription">Expense Description</label>
                            <input
                                type="text"
                                name="expenseDescription"
                                className="form-control"
                                placeholder="Add a description to this expense"
                                value={expense.expenseDescription}
                                onChange={(e) => handleChange(e)}
                                required
                            />
                            <div className="invalid-feedback">Expense Description is required</div>
                        </div>

                        <div className="form-group">
                            {/*PASSWORD*/}
                            <label htmlFor="expensePrice">Expense Price</label>
                            <input
                                type="number"
                                name="expensePrice"
                                className="form-control"
                                placeholder="Add an expense"
                                value={expense.expensePrice}
                                onChange={(e) => handleChange(e)}
                                required
                            />
                            <div className="invalid-feedback">Expense Price is required</div>
                        </div>

                        <div className="form-group">
                            {/*ExpenseDate*/}
                            <label htmlFor="expenseDate">Expense Date</label>
                            <input
                                type="date"
                                // type="datetime-local"
                                name="expenseDate"
                                className="form-control"
                                placeholder=""
                                value={expense.expenseDate}
                                onChange={(e) => handleChange(e)}

                            />
                            <div className="invalid-feedback">Expense Date/Time is required</div>
                        </div>
    
                        {/*Add the button*/}
                        <button className="btn btn-primary w-100 mt-3">Add Expense</button>
                        <button className="btn btn-danger w-100 mt-3">Reset</button>
                        {/*<button onClick={(clickMe)} className="btn btn-primary w-100 mt-5">Click me</button>*/}
                    </form>
                </div>
            </div>

    )

}

export default ExpenseForm;
